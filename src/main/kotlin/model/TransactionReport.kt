package org.example.model
// TODO: Change names to be more descriptive
data class Report (
    var income: Double,
    var expenses: Double,
    val fakeCategorySummaries: MutableMap<FakeCategory, Double> // TODO: Make it its own data class (for more readability)
) {
    fun getBalance(): Double {
        return income - expenses
    }
}

data class CategorySummary(
    val category: FakeCategory,
    val amount: Double
)

