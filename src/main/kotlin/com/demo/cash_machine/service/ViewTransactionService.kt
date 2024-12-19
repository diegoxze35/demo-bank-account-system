package com.demo.cash_machine.service

import com.demo.cash_machine.model.AccountDTO
import com.demo.cash_machine.repository.TransactionRepository
import com.demo.cash_machine.repository.entity.Account
import com.demo.cash_machine.repository.entity.Transaction
import org.springframework.stereotype.Service

@Service
class ViewTransactionService(
    private val transactionRepository: TransactionRepository
) {

    fun view(accountId: Int): List<Transaction> {
        return transactionRepository.findByAccountIdOrderByDateAndHour(accountId).take(5)
    }

}