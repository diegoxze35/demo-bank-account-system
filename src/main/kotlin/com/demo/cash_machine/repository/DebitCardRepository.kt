package com.demo.cash_machine.repository

import com.demo.cash_machine.repository.entity.Card
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface DebitCardRepository : JpaRepository<Card, Int> {

    @Modifying
    @Transactional
    @Query("UPDATE debit_card c SET c.balance = c.balance - :amount WHERE c.card_id = :cardId", nativeQuery = true)
    fun withdrawMoneyFromCard(@Param("cardId") cardId: Int, @Param("amount") amount: Double)

}