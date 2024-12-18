package com.demo.cash_machine.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Client(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id", columnDefinition = "BIGINT")
	val id: Int,
	
	@Column(
		name = "name",
		length = 50,
		nullable = false
	)
	val name: String
)
