package org.example.feature

import org.example.Transaction
import java.util.UUID

class TransactionOperations: TransactionService {

    val transactions = mutableListOf<Transaction>()
    // val transactions: List<Transaction> get() = _transactions

    override fun getTransactionById(id: UUID): Transaction? {
        return transactions.find { it.id == id }
    }

    override fun addTransaction(transaction: Transaction): Boolean {
        transactions.add(transaction)
        return true
    }

    override fun updateTransaction(id: UUID, transaction: Transaction): Boolean {
        val index = transactions.indexOfFirst { it.id == id}

        if (index != -1) transactions[index] = transaction

        return true
    }

    override fun deleteTransaction(id: UUID): Boolean {
        val transaction = transactions.find { it.id == id } ?: return false
        return transactions.remove(transaction)
    }

    override fun getAllTransactions(): List<Transaction> {
        return transactions.toList()
    }
}