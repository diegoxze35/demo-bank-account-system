package com.demo.cash_machine.model.request

data class WithdrawalRequest (
    val cardNumber: String,
    val nip: String,
    val amount: Double
)