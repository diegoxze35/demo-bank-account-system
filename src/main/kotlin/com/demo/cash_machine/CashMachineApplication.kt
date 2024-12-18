package com.demo.cash_machine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CashMachineApplication

fun main(args: Array<String>) {
	runApplication<CashMachineApplication>(*args)
}