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
	open val creditCardToPay: String?,
	open val serviceReference: String?
) {
	
	@JsonTypeName("card")
	data class CardMethodPaymentRequest(
		override val amount: Double,
		override val creditCardToPay: String?,
		override val serviceReference: String?,
		val credentials: AuthRequest
	) : MethodPaymentRequest(amount, creditCardToPay, serviceReference)
	
	@JsonTypeName("cash")
	data class CashMethodPaymentRequest(
		override val amount: Double,
		override val creditCardToPay: String?,
		override val serviceReference: String?,
		val accountId: Int?,
	) : MethodPaymentRequest(amount, creditCardToPay, serviceReference)
}