package com.demo.cash_machine.service.model

import com.demo.cash_machine.model.response.PaymentResponse

interface Payment {
	
	fun processPayment(method: MethodPayment): PaymentResponse
	
}