package com.demo.cash_machine.service.model

interface Payment {
	
	fun processPayment(method: MethodPayment): Boolean
	
}