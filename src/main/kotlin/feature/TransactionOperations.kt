package org.example.feature

import org.example.Transaction

class TransactionOperations {

    val transactions = mutableListOf<Transaction>()
    // val transactions: List<Transaction> get() = _transactions

    fun addTransaction(transaction: Transaction): Boolean {
        if (transaction.id < 0) return false

        if (transaction.amount <= 0.0 || transaction.amount.isNaN()) return false

        if (transaction.category.isBlank()) return false

        if (transactions.any { it.id == transaction.id }) return false

        transactions.add(transaction)
        return true
    }

    fun updateTransaction(id:Int, transaction: Transaction): Boolean {
        return false
    }
    fun displayAllTransactions() {
        if (transactions.isEmpty()) {
            println("No transactions found.")
        } else {
            transactions.forEach { transaction ->
                println(
                    "Transaction ${transaction.id} : at ${transaction.date} - with amount ${transaction.amount}$ " +
                            "- Category: ${transaction.category} - Description: ${transaction.description}"
                )
            }
        }
    }

}