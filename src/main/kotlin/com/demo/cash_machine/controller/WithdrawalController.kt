package com.demo.cash_machine.controller

import com.demo.cash_machine.model.request.WithdrawalRequest
import com.demo.cash_machine.model.response.WithdrawalResponse
import com.demo.cash_machine.service.WithdrawalService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class WithdrawalController(
    val withdrawalService: WithdrawalService
) {

    @PostMapping("/withdraw")
    fun withdraw(@RequestBody request: WithdrawalRequest): WithdrawalResponse {
        val (cardNumber, nip, amount) = request
        return withdrawalService.withdrawal(cardNumber, nip, amount)
    }
}