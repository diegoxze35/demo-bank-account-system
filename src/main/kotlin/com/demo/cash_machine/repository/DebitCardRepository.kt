package com.demo.cash_machine.repository

import com.demo.cash_machine.repository.entity.DebitCard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface DebitCardRepository : JpaRepository<DebitCard, Int> {

    @Modifying
    @Transactional
    @Query("UPDATE debit_card c SET c.balance = c.balance - :amount WHERE c.card_id = :cardId", nativeQuery = true)
    fun withdrawDebitCard(@Param("cardId") cardId: Int, @Param("amount") amount: Double)

    @Modifying
    @Transactional
    @Query("UPDATE debit_card c SET c.balance = c.balance + :amount WHERE c.card_id = :cardId", nativeQuery = true)
    fun depositToCard(@Param("cardId") cardId: Int, @Param("amount") amount: Double)

}