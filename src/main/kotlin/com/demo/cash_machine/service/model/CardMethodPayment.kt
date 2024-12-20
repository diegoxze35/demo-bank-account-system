package com.demo.cash_machine.service.model

import com.demo.cash_machine.model.response.MethodPaymentResponse
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
	
	override fun pay(amount: Double): MethodPaymentResponse {
		return when (card) {
			is CreditCard -> {
				val creditCard = creditCardRepository.findById(card.id).get()
				val newBalance = creditCard.creditBalance + amount
				if (newBalance <= creditCard.creditLimit) {
					creditCard.creditBalance = newBalance
					creditCardRepository.save(creditCard)
					MethodPaymentResponse.Success.CardPaymentSuccess("Payment successful!", newBalance)
				} else
					MethodPaymentResponse.Error.CardPaymentError(
						"You are trying to pay an amount greater than your credit limit, " +
								"your credit limit is ${creditCard.creditLimit}"
					)
			}
			
			is DebitCard -> {
				val debitCard = debitCardRepository.findById(card.id).get()
				val newBalance = debitCard.balance - amount
				if (newBalance >= 0.0) {
					debitCard.balance = newBalance
					debitCardRepository.save(debitCard)
					MethodPaymentResponse.Success.CardPaymentSuccess("Payment successful!", newBalance)
				} else
					MethodPaymentResponse.Error.CardPaymentError(
						"You don't have enough balance for this transaction! " +
								"currently, your balance is ${debitCard.balance}"
					)
			}
			
			else -> throw IllegalArgumentException("Card does not exist.")
		}
	}
	
}