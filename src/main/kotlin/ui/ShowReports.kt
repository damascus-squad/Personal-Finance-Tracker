package ui

import org.example.Transaction
import org.example.features.summary.TransactionSummary
import org.example.model.TransactionReport
import kotlin.collections.component1
import kotlin.collections.component2


fun createReport(transactions: List<Transaction>) {
    println("Welcome to the Personal Finance Tracker")

    while (true) {
        println("\n[1] Monthly Report")
        println("[2] Yearly Report")
        println("[3] Exit")

        print("\nSelect option: ")

        when (readlnOrNull()) {
            "1" -> {
                // Get and validate year
                val year = getValidatedInput("Enter year: ") { input ->
                    input.toIntOrNull()?.let { it > 0 } ?: false
                }

                // Get and validate month (1-12)
                val month = getValidatedInput("Enter month (1-12): ") { input ->
                    input.toIntOrNull()?.let { it in 1..12 } ?: false
                }

                printReport(
                    TransactionSummary(
                        transactions = transactions
                    ).getByMonth(year, month)
                )
            }
            "2" -> {
                // Get and validate year
                val year = getValidatedInput("Enter year: ") { input ->
                    input.toIntOrNull()?.let { it > 0 } ?: false
                }

                printReport(
                    TransactionSummary(
                        transactions = transactions
                    ).getByYear(year))
            }
            "3" -> {
                println("\nExiting application. Goodbye!")
                break
            }
            else -> println("\nInvalid option, try again.")
        }
    }
}

fun printReport(transactionReport: TransactionReport) {
    println("Income: ${transactionReport.income}")
    println("Expenses: ${transactionReport.expenses}")
    println("Balance: ${transactionReport.getBalance()}")
    println("###########################################")
    println("Categories:")
    transactionReport.categorySummaries.forEach { (category, categorySummary) ->
        println("\t${category}:")
        println("\t\tAmount: ${categorySummary.amount}")
        println("\t\tTransactions Count: ${categorySummary.transactionsCount}")
    }
}