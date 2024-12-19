package com.demo.cash_machine.model.request

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(
	JsonSubTypes.Type(value = MethodPaymentRequest.CardMethodPaymentRequest::class, name = "card"),
	JsonSubTypes.Type(value = MethodPaymentRequest.CashMethodPaymentRequest::class, name = "cash")
)
sealed class MethodPaymentRequest(
	open val amount: Double,
	open val accountId: Int,
	open val creditCardPaymentRequest: CreditCardPaymentRequest?
) {
	
	@JsonTypeName("card")
	data class CardMethodPaymentRequest(
		override val amount: Double,
		override val accountId: Int,
		override val creditCardPaymentRequest: CreditCardPaymentRequest?,
		val credentials: AuthRequest
	) : MethodPaymentRequest(amount, accountId, creditCardPaymentRequest)
	
	@JsonTypeName("cash")
	data class CashMethodPaymentRequest(
		override val amount: Double,
		override val accountId: Int,
		override val creditCardPaymentRequest: CreditCardPaymentRequest?
	) : MethodPaymentRequest(amount, accountId, creditCardPaymentRequest)
}