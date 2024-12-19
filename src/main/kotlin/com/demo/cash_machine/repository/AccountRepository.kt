package com.demo.cash_machine.repository

import com.demo.cash_machine.repository.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface AccountRepository : JpaRepository<Account, Int> {

    @Modifying
    @Transactional
    @Query("UPDATE account a SET a.sum_deposit = a.sum_deposit + :amount WHERE a.account_id = :account", nativeQuery = true)
    fun updateSumDeposit(@Param("account") account: Int, @Param("amount") amount: Double)

    @Modifying
    @Transactional
    @Query("UPDATE account a SET a.sum_withdrawal = a.sum_withdrawal + :amount WHERE a.account_id = :account", nativeQuery = true)
    fun updateSumWithdrawal(@Param("account") account: Int, @Param("amount") amount: Double)

}