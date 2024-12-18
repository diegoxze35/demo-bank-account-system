package com.demo.cash_machine.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.util.Date

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Card(
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "card_id", columnDefinition = "BIGINT")
	val id: Int,
	
	@Column(nullable = false, unique = true, length = 16)
	val number: String,
	
	@Column(nullable = false, length = 4)
	val nip: String,
	
	@Column(nullable = false, length = 3, unique = true)
	val cvc: String,
	
	@Column(name = "expiration_date", nullable = false)
	val expirationDate: Date,
	
	@ManyToOne(targetEntity = Account::class)
	@JoinColumn(name = "account_id")
	val account: Account
)