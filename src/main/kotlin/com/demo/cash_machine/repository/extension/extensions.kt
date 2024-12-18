package com.demo.cash_machine.repository.extension

import com.demo.cash_machine.model.AccountDTO
import com.demo.cash_machine.model.CardDTO
import com.demo.cash_machine.repository.entity.Account
import com.demo.cash_machine.repository.entity.Card
import com.demo.cash_machine.repository.entity.CreditCard
import com.demo.cash_machine.repository.entity.DebitCard

fun Account.toDomain(): AccountDTO = AccountDTO(
	clientName = client.name,
	cards = cards.map { it.toDomain() }
)

fun Card.toDomain(): CardDTO = when(this) {
	is DebitCard -> CardDTO.DebitCardDTO(
		number = number,
		nip = nip,
		cvc = cvc,
		expirationDate = expirationDate,
		balance = balance
	)
	is CreditCard -> CardDTO.CreditCard(
		number = number,
		nip = nip,
		cvc = cvc,
		expirationDate = expirationDate,
		closingDate = closingDate,
		paymentDate = paymentDate,
		creditLimit = creditLimit,
		creditBalance = creditBalance
	)
	else -> throw IllegalArgumentException()
}