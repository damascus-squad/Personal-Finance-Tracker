package org.example.feature

import org.example.Transaction
import org.example.storage.FileStorage
import org.example.storage.FileStorageFactory
import java.util.UUID

class TransactionMangerImp: TransactionManager {
    val transactions = mutableListOf<Transaction>()
    private val storage: FileStorage<Transaction> = FileStorageFactory.create("transactions.json")

    override fun getTransactionById(id: UUID): Transaction? {
        return transactions.find { it.id == id }
    }

    override fun addTransaction(transaction: Transaction): Boolean {
        val exists = transactions.any { it.id == transaction.id }

        if (!exists) {
            transactions.add(transaction)
            storage.save(transactions)
            return true
        }
        return false
    }

    override fun updateTransaction(id: UUID, transaction: Transaction): Boolean {
        val index = transactions.indexOfFirst { it.id == id }

        if (index != -1) {
            transactions[index] = transaction
            storage.save(transactions)
            return true
        }

        return false
    }

    override fun deleteTransaction(id: UUID): Boolean {
        val transaction = transactions.find { it.id == id } ?: return false
        transactions.remove(transaction)
        storage.overWrite(transactions)
        return true
    }

    override fun getAllTransactions(): List<Transaction> {
        if (transactions.isEmpty()){
            transactions.addAll(storage.load())
        }
        return transactions
    }
}