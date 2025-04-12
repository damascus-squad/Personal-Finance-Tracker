package ui

import model.Transaction
import org.example.ui.TerminalColor
import org.example.features.summary.TransactionSummary
import org.example.model.TransactionReport
import org.example.ui.withStyle
import kotlin.collections.component1
import kotlin.collections.component2


fun runReportsCLI(transactions: List<Transaction>) {
    while (true) {
        println("===== Personal Finance CLI=====".withStyle(TerminalColor.Blue))
        listOf(
            "Monthly Report",
            "Yearly Report",
            "Exit",
        ).forEachIndexed { index, item ->
            println("${index + 1}. $item".withStyle(TerminalColor.entries.random()))
        }
        println("==================================".withStyle(TerminalColor.Blue))
        print("Choose an option: ".withStyle(TerminalColor.Cyan))
        when (readlnOrNull()) {
            "1" -> {
                // Get and validate year
                val year = getValidatedInput("Enter year: ") { input ->
                    input.toIntOrNull()?.let { it > 0 } == true
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
                    input.toIntOrNull()?.let { it > 0 } == true
                }

                printReport(
                    TransactionSummary(
                        transactions = transactions
                    ).getByYear(year))
            }
            "3" -> {
                println("\nExiting application. Goodbye!".withStyle(TerminalColor.Blue))
                break
            }
            else -> println("\nInvalid option, try again.".withStyle(TerminalColor.Red))
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