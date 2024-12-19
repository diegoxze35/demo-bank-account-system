package com.demo.cash_machine.service

import com.demo.cash_machine.model.TransactionDTO
import com.demo.cash_machine.repository.TransactionRepository
import com.demo.cash_machine.repository.extension.toDomain
import org.springframework.stereotype.Service

@Service
class ViewTransactionService(
    private val transactionRepository: TransactionRepository
) {

    fun view(accountId: Int): List<TransactionDTO> {
        return transactionRepository.findByAccountIdOrderByDateAndHourDesc(accountId)
            .take(5)
            .map { it.toDomain() }
    }

}