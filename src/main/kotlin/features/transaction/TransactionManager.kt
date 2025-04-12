package features.transaction

import org.example.model.*
import java.util.UUID

interface TransactionManager {
    fun addTransaction(transaction: Transaction):Boolean
    fun updateTransaction(id:UUID, transaction: Transaction):Boolean
    fun deleteTransaction(id: UUID):Boolean
    fun getAllTransactions(): List<Transaction>
    fun getTransactionById(id: UUID): Transaction?
}