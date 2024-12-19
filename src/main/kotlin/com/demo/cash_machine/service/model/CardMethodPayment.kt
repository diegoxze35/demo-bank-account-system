package com.demo.cash_machine.service.model

import com.demo.cash_machine.repository.CreditCardRepository
import com.demo.cash_machine.repository.DebitCardRepository
import com.demo.cash_machine.repository.entity.Card
import com.demo.cash_machine.repository.entity.CreditCard
import com.demo.cash_machine.repository.entity.DebitCard

class CardMethodPayment(
	private val card: Card,
	private val debitCardRepository: DebitCardRepository,
	private val creditCardRepository: CreditCardRepository
) : MethodPayment {
	
	override fun pay(amount: Double): Boolean {
		return when (card) {
			is CreditCard -> {
				val creditCard = creditCardRepository.findById(card.id).get()
				val newBalance = creditCard.creditBalance + amount
				return if (newBalance <= creditCard.creditLimit) {
					creditCard.creditBalance = newBalance
					creditCardRepository.save(creditCard)
					true
				} else
					false
			}
			
			is DebitCard -> {
				val debitCard = debitCardRepository.findById(card.id).get()
				val newBalance = debitCard.balance - amount
				return if (newBalance >= 0.0) {
					debitCard.balance = newBalance
					debitCardRepository.save(debitCard)
					true
				} else
					false
			}
			
			else -> false
		}
	}
	
}