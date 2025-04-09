package org.example.model

data class Report (
    val income: Double,
    val expenses: Double,
    val fakeCategorySummaries: Map<FakeCategory, Int>
)

