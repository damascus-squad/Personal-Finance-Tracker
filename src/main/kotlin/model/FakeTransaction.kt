package org.example.model
import java.time.LocalDate

class FakeTransaction (
    val date: LocalDate,
    val amount: Double,
    val transactionType: FakeTransactionType,
    val category: FakeCategory
)

enum class FakeTransactionType {
    INCOME,EXPENSE
}