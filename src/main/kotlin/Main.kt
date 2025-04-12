package org.example

import org.example.model.*
import features.transaction.TransactionHelper.addTransaction
import features.transaction.TransactionHelper.deleteTransaction
import features.transaction.TransactionHelper.displayAllTransactions
import features.transaction.TransactionHelper.editTransaction
import features.transaction.TransactionManager
import features.transaction.TransactionMangerImp
import org.example.features.summary.TransactionSummary
import org.example.model.TransactionReport

fun main() {
    val manger = TransactionMangerImp()
    runCLI(manger)
}

fun runCLI(manager: TransactionManager) {
    while (true) {
        println(
            """
    ===== Personal Finance CLI=====
    1. Add Transaction
    2. Edit Transaction
    3. Delete Transaction
    4. List All Transactions
    5. Create Report
    6. Exit
    ==================================
          """.trimMargin()
        )
        print("Choose an option: ")
        when (readlnOrNull()?.trim()) {
            "1" -> addTransaction(manager)
            "2" -> editTransaction(manager)
            "3" -> deleteTransaction(manager)
            "4" -> displayAllTransactions(manager)
            "5" -> createReport(manager.getAllTransactions())
            "6" -> {
                println("Exiting... Goodbye!")
                return
            }

            else -> println("❌ Invalid choice!")
        }
        println("press any key to Continue")
        readlnOrNull()
    }
}

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
