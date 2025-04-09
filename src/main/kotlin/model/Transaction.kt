package org.example

import java.time.LocalDateTime

data class Transaction(
    val id: Int,
    var amount: Double,
    val transactionType: TransactionType,
    var date: LocalDateTime,
    var category: String,
    var description: String?
)

enum class TransactionType {
    INCOME,
    EXPENSE
}
