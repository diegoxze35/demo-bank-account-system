package com.demo.cash_machine.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.PrimaryKeyJoinColumn
import java.util.Date

@Entity
@PrimaryKeyJoinColumn(name = "card_id")
class DebitCard(
	@Column(name = "balance")
	var balance: Double,
	id: Int,
	number: String,
	nip: String,
	cvc: String,
	expirationDate: Date,
	account: Account
) : Card(id, number, nip, cvc, expirationDate, account)
