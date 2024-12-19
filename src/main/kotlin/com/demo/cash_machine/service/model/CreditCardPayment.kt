package com.demo.cash_machine.service.model

import com.demo.cash_machine.repository.CreditCardRepository
import com.demo.cash_machine.repository.entity.CreditCard

class CreditCardPayment(
	private val amount: Double,
	private val creditCardToPay: CreditCard,
	private val creditCardRepository: CreditCardRepository
) : Payment {
	override fun processPayment(method: MethodPayment): Boolean {
		if (amount > creditCardToPay.creditBalance) return false
		if (!method.pay(amount)) return false
		creditCardToPay.creditBalance -= amount
		creditCardRepository.save(creditCardToPay)
		return true
	}
}