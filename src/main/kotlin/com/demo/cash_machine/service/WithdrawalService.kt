package com.demo.cash_machine.service

import com.demo.cash_machine.model.response.WithdrawalResponse
import com.demo.cash_machine.repository.AccountRepository
import com.demo.cash_machine.repository.CardRepository
import com.demo.cash_machine.repository.CreditCardRepository
import com.demo.cash_machine.repository.DebitCardRepository
import com.demo.cash_machine.repository.entity.CreditCard
import com.demo.cash_machine.repository.entity.DebitCard
import org.springframework.stereotype.Service

@Service
class WithdrawalService(
    private val cardRepository: CardRepository,
    private val debitCardRepository: DebitCardRepository,
    private val creditCardRepository: CreditCardRepository,
    private val accountRepository: AccountRepository
) {

    fun withdrawal(cardNumber: String, nip: String, amount: Double): WithdrawalResponse {
        val card = cardRepository.findCardByNumberAndNip(cardNumber, nip) ?: return WithdrawalResponse("Card not found")

        if (card is DebitCard) {
            if (card.balance < amount) return WithdrawalResponse("Not enough money on your debit card")
            if (card.account.sumWithdrawal + amount > 50000) return WithdrawalResponse("Withdrawal limit exceeded")
            if (card.number.substring(0..3) == "2222")
                debitCardRepository.withdrawDebitCard(card.id, amount)
            else
                debitCardRepository.withdrawDebitCard(card.id, amount + 50)
        } else if (card is CreditCard) {
            if (card.number.substring(0..3) == "2222") {
                if (amount + card.creditBalance > card.creditLimit) return WithdrawalResponse("Not enough money on your credit card")
                creditCardRepository.withdrawCreditCard(card.id, amount)
            } else {
                if (amount + card.creditBalance + 50 > card.creditLimit) return WithdrawalResponse("Not enough money on your credit card")
                creditCardRepository.withdrawCreditCard(card.id, amount + 50)
            }
        }

        accountRepository.updateSumWithdrawal(card.account.id, amount)
        return WithdrawalResponse("successful")
    }
}