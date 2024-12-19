package com.demo.cash_machine.model.response

data class CheckServiceBalanceResponse(
	val serviceName: String,
	val balance: Double
)
