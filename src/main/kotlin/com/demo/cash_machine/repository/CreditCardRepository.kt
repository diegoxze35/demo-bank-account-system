package com.demo.cash_machine.repository

import com.demo.cash_machine.repository.entity.CreditCard
import org.springframework.data.jpa.repository.JpaRepository

interface CreditCardRepository : JpaRepository<CreditCard, Int>