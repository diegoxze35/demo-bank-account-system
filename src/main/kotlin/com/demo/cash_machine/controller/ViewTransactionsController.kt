package com.demo.cash_machine.controller

import com.demo.cash_machine.repository.entity.Transaction
import com.demo.cash_machine.service.ViewTransactionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ViewTransactionsController(
    private val viewTransactionService: ViewTransactionService
) {

    @PostMapping("/view")
    fun viewTransactions(@RequestBody request: Int): List<Transaction> {
        return viewTransactionService.view(request)
    }

}