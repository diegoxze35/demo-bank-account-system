package com.demo.cash_machine.model

import java.sql.Timestamp

data class TransactionDTO(
	val dateAndHour: Timestamp,
	val concept: String,
	val amount: Double
)
