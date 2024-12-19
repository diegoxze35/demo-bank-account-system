package com.demo.cash_machine.service.model


interface MethodPayment {
	
	fun pay(amount: Double): Boolean
	
}