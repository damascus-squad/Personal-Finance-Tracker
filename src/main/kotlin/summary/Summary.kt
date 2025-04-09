package org.example.summary

import org.example.model.FakeCategory
import org.example.model.FakeTransaction
import org.example.model.FakeTransactionType
import org.example.model.Report

class Summary {
    fun getByMonth(fakeTransactions: List<FakeTransaction>, month: Int, year: Int): Report {
        val monthlyReport = Report(0.0,0.0, mutableMapOf<FakeCategory,Double>())
        fakeTransactions.forEach { transcation ->
            if(transcation.date.year == year && transcation.date.month.value == month){
                when(transcation.fakeTransactionType){
                    FakeTransactionType.INCOME -> monthlyReport.income += transcation.amount
                    FakeTransactionType.EXPENSE -> monthlyReport.expenses += transcation.amount
                }
                monthlyReport.fakeCategorySummaries[transcation.fakeCategory] = transcation.amount
            }
        }
        printOutReport(monthlyReport)
        return monthlyReport

    }

    fun getByYear(fakeTransactions: List<FakeTransaction>, year: Int): Report {
        val monthlyReport = Report(0.0,0.0, mutableMapOf<FakeCategory,Double>())
        fakeTransactions.forEach { transcation ->
            if(transcation.date.year == year ){
                when(transcation.fakeTransactionType){
                    FakeTransactionType.INCOME -> monthlyReport.income += transcation.amount
                    FakeTransactionType.EXPENSE -> monthlyReport.expenses += transcation.amount
                }
                monthlyReport.fakeCategorySummaries[transcation.fakeCategory] = transcation.amount
            }
        }
        printOutReport(monthlyReport)
        return monthlyReport
    }

    private fun printOutReport(report: Report) {
        println("INCOME : ${report.income}")
        println("EXPENSES : ${report.expenses}")
        println("BALANCE : ${report.getBalance()}")
        println("###########################################")
        println("CATEGORIES")
        report.fakeCategorySummaries.forEach { category, amount ->
            println("$category : $amount")
        }
    }


}