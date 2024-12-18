package com.demo.cash_machine.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.PrimaryKeyJoinColumn
import java.util.Date

@Entity
@PrimaryKeyJoinColumn(name = "card_id")
class CreditCard(
	
	@Column(name = "closing_date")
	val closingDate: Date,
	
	@Column(name = "paymentDate")
	val paymentDate: Date,
	
	@Column(name = "credit_limit")
	val creditLimit: Double,
	
	@Column(name = "credit_balance")
	val creditBalance: Double,
	
	id: Int,
	number: String,
	nip: String,
	cvc: String,
	expirationDate: Date,
	account: Account
) : Card(
	id, number, nip, cvc,
	expirationDate,
	account
)