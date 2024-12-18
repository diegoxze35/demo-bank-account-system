package com.demo.cash_machine.model

import java.util.Date

sealed class CardDTO(
	open val number: String,
	open val nip: String,
	open val cvc: String,
	open val expirationDate: Date
) {
	data class DebitCardDTO(
		override val number: String,
		override val nip: String,
		override val cvc: String,
		override val expirationDate: Date,
		val balance: Double
	): CardDTO(number, nip, cvc, expirationDate)
	
	data class CreditCard(
		override val number: String,
		override val nip: String,
		override val cvc: String,
		override val expirationDate: Date,
		val closingDate: Date,
		val paymentDate: Date,
		val creditLimit: Double,
		val creditBalance: Double
	) : CardDTO(number, nip, cvc, expirationDate)
}
