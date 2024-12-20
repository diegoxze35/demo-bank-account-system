package com.demo.cash_machine.service.model

import com.demo.cash_machine.model.response.MethodPaymentResponse
import com.demo.cash_machine.model.response.PaymentResponse

class ServicePayment(
	private val onPaymentSuccess: () -> Unit,
	private val amount: Double //USER INPUT
) : Payment {
	
	override fun processPayment(method: MethodPayment): PaymentResponse {
		val response = method.pay(amount)
		if (response is MethodPaymentResponse.Success) {
			onPaymentSuccess()
		}
		return response
	}
}