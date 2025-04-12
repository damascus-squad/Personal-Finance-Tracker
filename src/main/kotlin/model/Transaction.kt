package model

import kotlinx.serialization.Serializable
import org.example.model.Category
import org.example.storage.LocalDateTimeSerializer
import org.example.storage.UUIDSerializer
import java.time.LocalDateTime
import java.util.*

@Serializable
data class Transaction(
    @Serializable(UUIDSerializer::class)
    val id: UUID,
    var amount: Double,
    val transactionType: TransactionType,
    @Serializable(LocalDateTimeSerializer::class)
    var date: LocalDateTime,
    var category: Category,
    var description: String?
)

enum class TransactionType {
    INCOME,
    EXPENSE
}
