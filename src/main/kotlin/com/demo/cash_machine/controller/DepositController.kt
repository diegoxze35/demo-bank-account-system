package com.demo.cash_machine.controller

import com.demo.cash_machine.model.request.DepositRequest
import com.demo.cash_machine.model.response.DepositResponse
import com.demo.cash_machine.service.DepositService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class DepositController(
    val depositService: DepositService
) {

    @PostMapping("/deposit")
    fun deposit(@RequestBody request: DepositRequest): DepositResponse {
        val (cardNumber, amount) = request
        return depositService.deposit(cardNumber, amount)
    }
}