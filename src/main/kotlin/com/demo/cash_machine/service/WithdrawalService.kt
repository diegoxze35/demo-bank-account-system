package com.demo.cash_machine.service

import com.demo.cash_machine.model.response.WithdrawalResponse
import com.demo.cash_machine.repository.AccountRepository
import com.demo.cash_machine.repository.CardRepository
import com.demo.cash_machine.repository.DebitCardRepository
import com.demo.cash_machine.repository.entity.CreditCard
import com.demo.cash_machine.repository.entity.DebitCard
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WithdrawalService(
    private val cardRepository: CardRepository,
    private val debitCardRepository: DebitCardRepository,
    private val accountRepository: AccountRepository
) {

    fun withdrawal(cardNumber: String, nip: String, amount: Double): WithdrawalResponse {
        val card = cardRepository.findCardByNumberAndNip(cardNumber, nip) ?: return WithdrawalResponse("Card not found")

        if (card is DebitCard) {
            if (card.balance < amount) return WithdrawalResponse("Not enough money on your debit card")
            if (card.account.sumWithdrawal + amount > 50000) return WithdrawalResponse("Withdrawal limit exceeded")
            if (card.number.substring(0, 3) == "2222")
                debitCardRepository.withdrawMoneyFromCard(card.id, amount)
            else
                debitCardRepository.withdrawMoneyFromCard(card.id, amount + 50)
        } else if (card is CreditCard) return WithdrawalResponse("Credit card can't be used for withdrawal")

        accountRepository.updateSumWithdrawal(card.account.id, amount)
        return WithdrawalResponse("successful")
    }
}