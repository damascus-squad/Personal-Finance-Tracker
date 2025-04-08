package org.example.model

data class Report (
    val income: Int,
    val expenses: Int,
    val fakeCategorySummaries: Map<FakeCategory, Int>
)

