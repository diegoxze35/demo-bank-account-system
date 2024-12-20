package com.demo.cash_machine.service.model

import com.demo.cash_machine.model.response.MethodPaymentResponse
import com.demo.cash_machine.model.response.PaymentResponse
import com.demo.cash_machine.repository.CreditCardRepository
import com.demo.cash_machine.repository.entity.CreditCard

class CreditCardPayment(
	private val amount: Double,
	private val creditCardToPay: CreditCard,
	private val creditCardRepository: CreditCardRepository
) : Payment {
	override fun processPayment(method: MethodPayment): PaymentResponse {
		return when (val response = method.pay(amount)) {
			is MethodPaymentResponse.Error -> response
			is MethodPaymentResponse.Success -> {
				creditCardToPay.creditBalance -= amount
				creditCardRepository.save(creditCardToPay)
				response
			}
		}
	}
}