# Endpoints

---

## View transactions

| Route    | Type |
|----------|------|
| api/view | post |

Receives an Int (accountId) and returns the last 5 transactions, ordered by date and time in descending order

---

## Deposit

| Route       | Type |
|-------------|------|
| api/deposit | post |

Receives a card number and an amount

```json
{
  "cardNumber": String,
  "amount": Double
}
```

1. Checks if the card is a debit card:
    - If the total deposits plus the current amount exceed the daily limit (50,000), it returns an error message: "
      Deposit limit exceeded."
    - Otherwise, it deposits the amount into the card and updates the total daily deposits.
2. Checks if the card is a credit card:
    - It returns an error message: "Credit card can't be used for deposit."
3. Returns a success message when the operation completes successfully

---

## Withdrawal

| Route        | Type |
|--------------|------|
| api/withdraw | post |

Receives a card number, nip an amount

```json
{
  "cardNumber": String,
  "nip": String,
  "amount": Double
}
```

1. Checks if the card is a debit card:
    - If the balance is insufficient, it returns an error message: "Not enough money on your debit card."
    - If the daily withdrawal limit is exceeded, it returns another error message: "Withdrawal limit exceeded."
    - For card numbers starting with "2222", withdraw the exact amount. Otherwise, it adds 50$ (commission).
2. Checks if the card is a credit card:
    - It returns an error message: "Credit card can't be used for withdrawal."
3. Updates the total daily withdrawals:
    - The withdrawal amount is logged to ensure the daily limit is not exceeded.
4. Returns a success message when the operation completes successfully.