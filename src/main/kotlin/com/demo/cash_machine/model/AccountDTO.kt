package com.demo.cash_machine.model

data class AccountDTO(
  val accountId: Int,
	val clientName: String,
	val cards: List<CardDTO>
)
