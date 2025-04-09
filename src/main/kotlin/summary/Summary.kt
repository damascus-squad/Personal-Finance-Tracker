package org.example.summary

import org.example.model.FakeCategory
import org.example.model.FakeTransaction
import org.example.model.Report

class Summary {
    fun getByMonth(fakeTransactions: List<FakeTransaction>, month: Int, year: Int): Report {
        return Report (
            0.0,
            0.0,
            mapOf(
                Pair(FakeCategory.SALARY, 0)
            )
        )
    }

    fun getByYear(fakeTransactions: List<FakeTransaction>, year: Int): Report {
        return Report (
            0.0,
            0.0,
            mapOf(
                Pair(FakeCategory.SALARY, 0)
            )
        )
    }
}