package com.demo.cash_machine.service.model

import com.demo.cash_machine.repository.TransactionRepository
import com.demo.cash_machine.repository.entity.Account
import com.demo.cash_machine.repository.entity.Transaction
import java.sql.Timestamp
import java.time.LocalDateTime

class CashMethodPayment(
	private val transactionRepository: TransactionRepository,
	private val account: Account
) : MethodPayment {
	override fun pay(amount: Double): Boolean {
		val transaction = Transaction(
			dateAndHour = Timestamp.valueOf(LocalDateTime.now()),
			concept = "Cash Payment",
			amount = amount,
			account =  account
		)
		return transactionRepository.save(transaction).account.id == account.id
	}
}