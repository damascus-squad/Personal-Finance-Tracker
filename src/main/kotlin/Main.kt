package org.example

import org.example.features.summary.TransactionSummary
import org.example.model.FakeCategory
import org.example.model.FakeTransaction
import org.example.model.FakeTransactionType
import org.example.model.TransactionReport
import java.time.LocalDate

val summary = TransactionSummary(
    listOf(
        FakeTransaction(
            LocalDate.now(),
            amount = 200.0,
            transactionType = FakeTransactionType.INCOME,
            category = FakeCategory.SALARY,
        )
    )
)

fun printOutReport(transactionReport: TransactionReport) {
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

fun main() {
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

                printOutReport(summary.getByMonth(year, month))
            }
            "2" -> {
                // Get and validate year
                val year = getValidatedInput("Enter year: ") { input ->
                    input.toIntOrNull()?.let { it > 0 } ?: false
                }

                printOutReport(summary.getByYear(year))
            }
            "3" -> {
                println("\nExiting application. Goodbye!")
                break
            }
            else -> println("\nInvalid option, try again.")
        }
    }
}

/**
 * Gets user input and validates it using the provided validation function.
 * Keeps asking until valid input is provided.
 *
 * @param prompt The message to display when asking for input
 * @param validator A function that returns true if input is valid
 * @return The validated input as an integer
 */
fun getValidatedInput(prompt: String, validator: (String) -> Boolean): Int {
    while (true) {
        print(prompt)
        val input = readlnOrNull() ?: ""

        if (validator(input)) {
            return input.toInt()
        } else {
            println("Invalid input. Please try again.")
        }
    }
}