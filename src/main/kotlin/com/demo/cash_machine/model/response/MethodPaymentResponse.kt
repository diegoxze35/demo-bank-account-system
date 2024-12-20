package com.demo.cash_machine.model.response

sealed class MethodPaymentResponse(override val message: String) : PaymentResponse(message) {
	
	sealed class Success(override val message: String) : MethodPaymentResponse(message) {
		
		data class CardPaymentSuccess(
			override val message: String,
			val newBalance: Double
		) : Success(message)
		
		data class CashPaymentSuccess(
			override val message: String,
			val change: Double
		) : Success(message)
		
	}
	
	sealed class Error(override val message: String) : MethodPaymentResponse(message) {
		data class CardPaymentError(
			override val message: String
		) : Error(message)
		
		data class CashPaymentError(override val message: String) : Error(message)
	}
	
	
}