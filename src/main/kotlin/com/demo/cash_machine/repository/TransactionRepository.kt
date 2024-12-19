package com.demo.cash_machine.repository

import com.demo.cash_machine.repository.entity.Transaction
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository : JpaRepository<Transaction, Int> {

    fun findByAccountIdOrderByDateAndHour(accountId: Int): List<Transaction>

}