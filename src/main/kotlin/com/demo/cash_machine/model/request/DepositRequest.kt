package com.demo.cash_machine.model.request

data class DepositRequest(
    val cardNumber: String,
    val amount: Double
)