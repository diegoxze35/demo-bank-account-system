package com.demo.cash_machine.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne

@Entity
data class Account(

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id", columnDefinition = "BIGINT")
	val id: Int,

	@Column(name = "sum_withdrawal", nullable = false, columnDefinition = "double default 0.0")
	val sumWithdrawal: Double = 0.0,

    @Column(name = "sum_deposit", nullable = false, columnDefinition = "double default 0.0")
	val sumDeposit: Double = 0.0,

	@OneToOne(targetEntity = Client::class)
	@JoinColumn(name = "client_id")
	val client: Client,

	@OneToMany(targetEntity = Card::class, fetch = FetchType.EAGER, mappedBy = "account")
	val cards: List<Card>,

	@OneToMany(targetEntity = Transaction::class, fetch = FetchType.LAZY, mappedBy = "account")
	val transactions: List<Transaction>
)
