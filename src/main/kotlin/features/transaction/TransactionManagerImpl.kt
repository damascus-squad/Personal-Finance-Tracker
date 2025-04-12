package org.example.feature

import org.example.Transaction
import org.example.storage.FileStorage
import org.example.storage.FileStorageFactory
import java.util.UUID

class TransactionMangerImpl(
    private val storage: FileStorage<Transaction>
): TransactionManager {
    private val transactions = mutableListOf<Transaction>()


    override fun getById(id: UUID): Transaction? {
        return transactions.find { it.id == id }
    }

    override fun add(transaction: Transaction): Boolean {
        val exists = transactions.any { it.id == transaction.id }

        if (!exists) {
            transactions.add(transaction)
            storage.save(transactions)
            return true
        }
        return false
    }

    override fun update(id: UUID, transaction: Transaction): Boolean {
        val index = transactions.indexOfFirst { it.id == id }

        if (index != -1) {
            transactions[index] = transaction
            storage.save(transactions, overwrite = true)
            return true
        }

        return false
    }

    override fun delete(id: UUID): Boolean {
        val transaction = transactions.find { it.id == id } ?: return false
        transactions.remove(transaction)
        storage.save(transactions, overwrite = true)
        return true
    }

    override fun getAll(): List<Transaction> {
        if (transactions.isEmpty()){
            transactions.addAll(storage.load())
        }
        return transactions
    }
}