package com.demo.cash_machine.service

import com.demo.cash_machine.model.request.MethodPaymentRequest
import com.demo.cash_machine.model.response.CheckServiceBalanceResponse
import com.demo.cash_machine.repository.AccountRepository
import com.demo.cash_machine.repository.CardRepository
import com.demo.cash_machine.repository.CreditCardRepository
import com.demo.cash_machine.repository.DebitCardRepository
import com.demo.cash_machine.repository.TransactionRepository
import com.demo.cash_machine.repository.entity.CreditCard
import com.demo.cash_machine.service.model.CardMethodPayment
import com.demo.cash_machine.service.model.CashMethodPayment
import com.demo.cash_machine.service.model.CreditCardPayment
import com.demo.cash_machine.service.model.MethodPayment
import com.demo.cash_machine.service.model.Payment
import com.demo.cash_machine.service.model.ServicePayment
import org.springframework.stereotype.Service
import kotlin.math.roundToInt
import kotlin.random.Random

@Service
class PaymentService(
	private val cardRepository: CardRepository,
	private val creditCardRepository: CreditCardRepository,
	private val debitCardRepository: DebitCardRepository,
	private val transactionRepository: TransactionRepository,
	private val accountRepository: AccountRepository
) {
	
	fun checkBalanceOfService(reference: String): CheckServiceBalanceResponse? {
		
		val validReferences = mapOf(
			"122" to "Water",
			"224" to "Gas",
			"352" to "Electricity"
		)
		val serviceKey = reference.substring(0..2)
		return if (serviceKey !in validReferences.keys)
			null
		else {
			val balance = (0..499).random().toDouble() + (Random.nextDouble() * 100).roundToInt() / 100
			CheckServiceBalanceResponse(
				serviceName = validReferences.getValue(serviceKey),
				balance = balance,
			)
		}
	}
	
	fun processPayment(methodPaymentRequest: MethodPaymentRequest): Boolean {
		val methodPayment: MethodPayment = when (methodPaymentRequest) {
			is MethodPaymentRequest.CardMethodPaymentRequest -> {
				val (number, nip) = methodPaymentRequest.credentials
				val card = cardRepository.findCardByNumberAndNip(number, nip) ?: return false
				CardMethodPayment(card, debitCardRepository, creditCardRepository)
			}
			
			is MethodPaymentRequest.CashMethodPaymentRequest -> {
				val account = accountRepository.findById(methodPaymentRequest.accountId).get()
				CashMethodPayment(transactionRepository, account)
			}
		}
		
		val payment: Payment = methodPaymentRequest.creditCardPaymentRequest?.let {
			val creditCardToPay = cardRepository.findCardByNumber(it.cardNumber)
			if (creditCardToPay !is CreditCard) return false
			CreditCardPayment(methodPaymentRequest.amount, creditCardToPay, creditCardRepository)
		} ?: ServicePayment(methodPaymentRequest.amount)
		
		return payment.processPayment(methodPayment)
		
	}
	
}