package com.demo.cash_machine.repository

import com.demo.cash_machine.repository.entity.Card
import org.springframework.data.jpa.repository.JpaRepository

interface CardRepository : JpaRepository<Card, Int> {

    fun findCardByNumberAndNip(number: String, nip: String): Card?

}