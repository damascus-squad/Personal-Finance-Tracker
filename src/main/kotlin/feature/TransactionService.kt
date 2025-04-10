package org.example.feature

import org.example.Transaction
import java.util.UUID

interface TransactionService {
    fun addTransaction(transaction: Transaction):Boolean
    fun updateTransaction(id:UUID, transaction: Transaction):Boolean
    fun deleteTransaction(id: UUID):Boolean
    fun getAllTransactions(): List<Transaction>
    fun getTransactionById(id: UUID): Transaction?
}