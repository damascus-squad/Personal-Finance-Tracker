package features.transaction


import model.Transaction
import java.util.UUID

interface TransactionManager {
    fun add(transaction: Transaction):Boolean
    fun update(id:UUID, transaction: Transaction):Boolean
    fun delete(id: UUID):Boolean
    fun getAll(): List<Transaction>
    fun getById(id: UUID): Transaction?
}