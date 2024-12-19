package com.demo.cash_machine.repository

import com.demo.cash_machine.repository.entity.DebitCard
import org.springframework.data.jpa.repository.JpaRepository

interface DebitCardRepository : JpaRepository<DebitCard, Int>