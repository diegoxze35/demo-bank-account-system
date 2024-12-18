package com.demo.cash_machine.model.request

data class AuthRequest(
	val cardNumber: String,
	val nip: String
)
