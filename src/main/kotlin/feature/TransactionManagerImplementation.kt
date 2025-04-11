package org.example.feature

import org.example.Transaction
import java.util.UUID

class TransactionManagerImplementation: TransactionManager {

    val transactions = mutableListOf<Transaction>()
    // val transactions: List<Transaction> get() = _transactions

    override fun getTransactionById(id: UUID): Transaction? {
        return transactions.find { it.id == id }
    }

    override fun addTransaction(transaction: Transaction): Boolean {
        val exists = transactions.any { it.id == transaction.id }

        if (!exists) {
            transactions.add(transaction)
            return true
        }

        return false
    }

    override fun updateTransaction(id: UUID, transaction: Transaction): Boolean {
        val index = transactions.indexOfFirst { it.id == id }

        if (index != -1) {
            transactions[index] = transaction
            return true
        }

        return false
    }

    override fun deleteTransaction(id: UUID): Boolean {
        val transaction = transactions.find { it.id == id } ?: return false
        return transactions.remove(transaction)
    }

    override fun getAllTransactions(): List<Transaction> {
        return transactions.toList()
    }
}