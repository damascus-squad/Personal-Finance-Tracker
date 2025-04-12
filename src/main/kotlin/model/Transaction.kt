package org.example.model

import java.time.LocalDateTime
import java.util.*

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
