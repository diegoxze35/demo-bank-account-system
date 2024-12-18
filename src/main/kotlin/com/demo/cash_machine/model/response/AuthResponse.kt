package com.demo.cash_machine.model.response

import com.demo.cash_machine.model.AccountDTO

data class AuthResponse(
	val clientAccount: AccountDTO?,
	val message: String
)
