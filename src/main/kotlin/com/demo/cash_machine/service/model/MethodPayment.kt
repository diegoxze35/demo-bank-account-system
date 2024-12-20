package com.demo.cash_machine.service.model

import com.demo.cash_machine.model.response.MethodPaymentResponse


interface MethodPayment {
	
	fun pay(amount: Double): MethodPaymentResponse
	
}