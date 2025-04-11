package org.example.features.summary

import org.example.model.FakeCategory
import org.example.model.FakeTransaction
import org.example.model.FakeTransactionType
import org.example.model.Report
// TODO: Apply dependency inversion
class TransactionSummary {

    // TODO: Make list a member variable in the class


    // TODO: Create one generic function with month as nullable with default param NULL

    fun getByMonth(fakeTransactions: List<FakeTransaction>, month: Int, year: Int): Report {
        val monthlyReport = Report(0.0,0.0, mutableMapOf<FakeCategory,Double>())
        fakeTransactions.forEach { transaction ->
            if(transaction.date.year == year && transaction.date.month.value == month){
                when(transaction.fakeTransactionType){
                    FakeTransactionType.INCOME -> monthlyReport.income += transaction.amount
                    FakeTransactionType.EXPENSE -> monthlyReport.expenses += transaction.amount
                }

                // FIXME: possible bug in = and not +=
                monthlyReport.fakeCategorySummaries[transaction.fakeCategory] = transaction.amount
            }
        }
        // TODO: move print to the CLI functions
//        printOutReport(monthlyReport)
        return monthlyReport

    }

    fun getByYear(fakeTransactions: List<FakeTransaction>, year: Int): Report {
        val monthlyReport = Report(0.0,0.0, mutableMapOf<FakeCategory,Double>())
        fakeTransactions.forEach { transaction ->
            if(transaction.date.year == year ){
                when(transaction.fakeTransactionType){
                    FakeTransactionType.INCOME -> monthlyReport.income += transaction.amount
                    FakeTransactionType.EXPENSE -> monthlyReport.expenses += transaction.amount
                }
                monthlyReport.fakeCategorySummaries[transaction.fakeCategory] = transaction.amount
            }
        }
//        printOutReport(monthlyReport)
        return monthlyReport
    }

    private fun printOutReport(report: Report) {
        println("INCOME : ${report.income}")
        println("EXPENSES : ${report.expenses}")
        println("BALANCE : ${report.getBalance()}")
        println("###########################################")
        println("CATEGORIES")
        report.fakeCategorySummaries.forEach { (category, amount) ->
            println("$category : $amount")
        }
    }


}