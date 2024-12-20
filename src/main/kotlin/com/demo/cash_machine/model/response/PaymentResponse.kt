package com.demo.cash_machine.model.response

sealed class PaymentResponse(open val message: String) {
	data object BadRequest : PaymentResponse("A service reference or credit card number is required")
	data object CardNotFound : PaymentResponse("Your card is invalid")
	data object CardIsNotCreditCard : PaymentResponse("This card is not a credit card")
}
