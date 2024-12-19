package com.demo.cash_machine.service.model

class ServicePayment(private val amount: Double) : Payment {
	override fun processPayment(method: MethodPayment): Boolean {
		return method.pay(amount)
	}
}