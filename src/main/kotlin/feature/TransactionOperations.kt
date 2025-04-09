package org.example.feature

import org.example.Transaction

class TransactionOperations {

    val transactions = mutableListOf<Transaction>()
   // val transactions: List<Transaction> get() = _transactions

    fun addTransaction(transaction: Transaction): Boolean {
        return true
    }



}