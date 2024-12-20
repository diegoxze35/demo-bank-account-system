package com.demo.cash_machine.service

import com.demo.cash_machine.model.request.MethodPaymentRequest
import com.demo.cash_machine.model.response.CheckServiceBalanceResponse
import com.demo.cash_machine.model.response.PaymentResponse
import com.demo.cash_machine.repository.AccountRepository
import com.demo.cash_machine.repository.CardRepository
import com.demo.cash_machine.repository.CreditCardRepository
import com.demo.cash_machine.repository.DebitCardRepository
import com.demo.cash_machine.repository.TransactionRepository
import com.demo.cash_machine.repository.entity.Account
import com.demo.cash_machine.repository.entity.CreditCard
import com.demo.cash_machine.service.model.CardMethodPayment
import com.demo.cash_machine.service.model.CashMethodPayment
import com.demo.cash_machine.service.model.CreditCardPayment
import com.demo.cash_machine.service.model.MethodPayment
import com.demo.cash_machine.service.model.Payment
import com.demo.cash_machine.service.model.ServicePayment
import org.springframework.stereotype.Service

@Service
class PaymentService(
	private val cardRepository: CardRepository,
	private val creditCardRepository: CreditCardRepository,
	private val debitCardRepository: DebitCardRepository,
	private val transactionRepository: TransactionRepository,
	private val accountRepository: AccountRepository
) {
	
	private val servicesWithBalance = mutableMapOf(
		/*Water service*/
		"1220102030405" to 1599.99,
		"1220102030406" to 20_000.0,
		"1220102030407" to 25_670.5,
		"1220102030408" to 15_524.25,
		"1220102030409" to 342.15,
		/*Water service*/
		
		/*Gas Service*/
		"2240102030405" to 400.0,
		"2240102030406" to 300.0,
		"2240102030407" to 3000.19,
		"2240102030408" to 90_781.56,
		"2240102030409" to 75_901.92,
		/*Gas Service*/
		
		/*Electricity service*/
		"3520102030405" to 100.05,
		"3520102030406" to 550.06,
		"3520102030407" to 220.00,
		"3520102030408" to 330.38,
		"3520102030409" to 375.00
		/*Electricity service*/
	)
	
	private val validReferences = mapOf(
		"122" to "Water",
		"224" to "Gas",
		"352" to "Electricity"
	)
	
	fun checkBalanceOfService(reference: String): CheckServiceBalanceResponse? =
		if (reference !in servicesWithBalance.keys)
			null
		else {
			val serviceKey = reference.substring(0..2)
			CheckServiceBalanceResponse(
				serviceName = validReferences.getValue(serviceKey),
				balance = servicesWithBalance.getValue(reference),
			)
		}
	
	
	fun processPayment(methodPaymentRequest: MethodPaymentRequest): PaymentResponse {
		
		var cost = 0.0
		
		val payment: Payment = methodPaymentRequest.creditCardToPay?.let {
			val creditCardToPay = cardRepository.findCardByNumber(it)
			if (creditCardToPay !is CreditCard) return PaymentResponse.CardIsNotCreditCard
			cost = creditCardToPay.creditBalance
			CreditCardPayment(
				methodPaymentRequest.amount, //USER INPUT
				creditCardToPay,
				creditCardRepository
			)
		} ?: methodPaymentRequest.serviceReference?.let {
			cost = servicesWithBalance.getValue(it)
			ServicePayment(
				amount = methodPaymentRequest.amount, //USER INPUT
				onPaymentSuccess = {
					servicesWithBalance[it] = 0.0
				}
			)
		} ?: return PaymentResponse.BadRequest
		
		val methodPayment: MethodPayment = when (methodPaymentRequest) {
			is MethodPaymentRequest.CardMethodPaymentRequest -> {
				val (number, nip) = methodPaymentRequest.credentials
				val card = cardRepository.findCardByNumberAndNip(number, nip) ?: return PaymentResponse.CardNotFound
				CardMethodPayment(card, debitCardRepository, creditCardRepository)
			}
			
			is MethodPaymentRequest.CashMethodPaymentRequest -> {
				val account: Account? = methodPaymentRequest.accountId?.let { accountRepository.findById(it).get() }
				CashMethodPayment(transactionRepository, account, cost = cost)
			}
		}
		
		
		return payment.processPayment(methodPayment)
		
	}
	
}