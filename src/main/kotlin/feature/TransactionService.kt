package org.example.feature

import org.example.Transaction

interface TransactionService {
    fun addTransaction(transaction: Transaction):Boolean
    fun updateTransaction(id:Int, transaction: Transaction):Boolean
    fun deleteTransaction(id: Int):Boolean
    fun listTransactions(): List<Transaction>
    fun getTransactionById(id: Int): Transaction?
}