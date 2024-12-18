package com.demo.cash_machine.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.sql.Timestamp

@Entity
data class Transaction(
	
	@Id
	@Column(name = "transaction_id", columnDefinition = "BIGINT")
	val id: Int,
	
	@Column(name = "date", nullable = false)
	val dateAndHour: Timestamp,
	
	@Column(length = 15, nullable = false)
	val concept: String,
	
	@Column(nullable = false)
	val amount: Double,
	
	@ManyToOne(targetEntity = Account::class)
	@JoinColumn(name = "account_id")
	val account: Account

)
