package com.demo.cash_machine.repository

import com.demo.cash_machine.repository.entity.CreditCard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface CreditCardRepository : JpaRepository<CreditCard, Int>{

    @Modifying
    @Transactional
    @Query("UPDATE credit_card c SET c.credit_balance = c.credit_balance + :amount WHERE c.card_id = :cardId", nativeQuery = true)
    fun withdrawCreditCard(@Param("cardId") cardId: Int, @Param("amount") amount: Double)
}