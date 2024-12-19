package com.demo.cash_machine.controller

import com.demo.cash_machine.model.request.MethodPaymentRequest
import com.demo.cash_machine.model.response.CheckServiceBalanceResponse
import com.demo.cash_machine.service.PaymentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PaymentController(
	private val paymentService: PaymentService
) {
	
	@GetMapping("/check-balance-service/{reference}")
	fun checkBalanceOfService(@PathVariable reference: String): ResponseEntity<CheckServiceBalanceResponse> {
		return ResponseEntity.ok(paymentService.checkBalanceOfService(reference))
	}
	
	@PostMapping("/pay")
	fun pay(@RequestBody request: MethodPaymentRequest): ResponseEntity<Boolean> =
		with(paymentService.processPayment(request)) {
			ResponseEntity(this, if (this) HttpStatus.OK else HttpStatus.BAD_REQUEST)
		}
	
	
}