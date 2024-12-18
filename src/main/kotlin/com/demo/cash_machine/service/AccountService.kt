package com.demo.cash_machine.service

import com.demo.cash_machine.model.response.AuthResponse
import com.demo.cash_machine.repository.CardRepository
import com.demo.cash_machine.repository.extension.toDomain
import org.springframework.stereotype.Service

@Service
class AccountService(
	private val cardRepository: CardRepository,
) {
	
	fun getAccountByCardNumberAndNip(cardNumber: String, nip: String): AuthResponse {
		val card = cardRepository.findCardByNumberAndNip(cardNumber, nip) ?: return AuthResponse(
			clientAccount = null,
			message = "Card number or nip is wrong"
		)
		return AuthResponse(card.account.toDomain(), "Success")
	}
	
}