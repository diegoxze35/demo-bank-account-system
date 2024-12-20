package com.demo.cash_machine.service

import com.demo.cash_machine.model.response.DepositResponse
import com.demo.cash_machine.repository.AccountRepository
import com.demo.cash_machine.repository.CardRepository
import com.demo.cash_machine.repository.DebitCardRepository
import com.demo.cash_machine.repository.entity.CreditCard
import com.demo.cash_machine.repository.entity.DebitCard
import org.springframework.stereotype.Service

@Service
class DepositService(
    private val cardRepository: CardRepository,
    private val debitCardRepository: DebitCardRepository,
    private val accountService: AccountRepository
) {

    fun deposit(cardNUmber: String, amount: Double): DepositResponse {
        val card = cardRepository.findCardByNumber(cardNUmber) ?: return DepositResponse("Card not found")

        if (card is DebitCard) {
            if (card.account!!.sumDeposit + amount > 50000) return DepositResponse("Deposit limit exceeded")
            debitCardRepository.depositToCard(card.id, amount)
            accountService.updateSumDeposit(card.account!!.id, amount)
        } else if (card is CreditCard) return DepositResponse("Credit card can't be used for deposit")

        return DepositResponse("Successful")
    }

}