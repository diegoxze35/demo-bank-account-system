package com.demo.cash_machine.controller

import com.demo.cash_machine.model.request.AuthRequest
import com.demo.cash_machine.model.response.AuthResponse
import com.demo.cash_machine.service.AccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AuthController(
	private val accountService: AccountService
) {
	
	@PostMapping("/auth")
	fun authenticateClient(@RequestBody request: AuthRequest): ResponseEntity<AuthResponse> {
		val (cardNumber, nip) = request
		return ResponseEntity(accountService.getAccountByCardNumberAndNip(cardNumber, nip), HttpStatus.OK)
	}
	
}