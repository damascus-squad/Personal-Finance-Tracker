package org.example.model

data class CategorySummary(
    var amount: Double = 0.0,
    var transactionsCount: Int = 0
)

data class TransactionReport (
    var income: Double,
    var expenses: Double,
    val categorySummaries: MutableMap<Category, CategorySummary>
) {
    fun getBalance(): Double = income - expenses
}
