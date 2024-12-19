package com.demo.cash_machine.repository

import com.demo.cash_machine.repository.entity.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Int> {

}