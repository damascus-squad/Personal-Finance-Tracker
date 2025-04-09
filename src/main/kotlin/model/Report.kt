package org.example.model

data class Report (
    var income: Double,
    var expenses: Double,
    val fakeCategorySummaries: MutableMap<FakeCategory, Double>
){
    fun getBalance():Double{
        return income - expenses
    }
}

