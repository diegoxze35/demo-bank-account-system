package com.demo.cash_machine.service.model

import com.demo.cash_machine.model.response.MethodPaymentResponse
import com.demo.cash_machine.repository.TransactionRepository
import com.demo.cash_machine.repository.entity.Account
import com.demo.cash_machine.repository.entity.Transaction
import java.sql.Timestamp
import java.time.LocalDateTime

class CashMethodPayment(
	private val transactionRepository: TransactionRepository,
	private val account: Account?,
	private val cost: Double /*SERVICE COST*/
) : MethodPayment {
	override fun pay(amount: Double/*USER INPUT*/): MethodPaymentResponse {
		val enoughMoney = amount >= cost
		if (!enoughMoney) return MethodPaymentResponse.Error.CashPaymentError(
			"Not enough money to complete your payment, " +
					"return your money..."
		)
		account?.let {
			val transaction = Transaction(
				dateAndHour = Timestamp.valueOf(LocalDateTime.now()),
				concept = "Cash Payment",
				amount = -cost,
				account = it
			)
			transactionRepository.save(transaction)
		}
		return MethodPaymentResponse.Success.CashPaymentSuccess("Payment successful!", amount - cost)
	}
}