package org.example

import java.time.LocalDateTime
import java.util.UUID

data class Transaction(
    val id: UUID,
    var amount: Double,
    val transactionType: TransactionType,
    var date: LocalDateTime,
    var category: Category,
    var description: String?
)

enum class TransactionType {
    INCOME,
    EXPENSE
}

data class Category(val id:Int, val name:String)
