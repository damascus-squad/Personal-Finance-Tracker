package ui

import model.Transaction
import org.example.features.summary.TransactionSummary
import org.example.ui.TerminalColor
import org.example.ui.printColoredTable
import org.example.ui.withStyle


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
                    input.toIntOrNull()?.let { it in 1..12 } == true
                }

                TransactionSummary(transactions = transactions)
                    .getByMonth(year, month)
                    .printColoredTable()
            }

            "2" -> {
                // Get and validate year
                val year = getValidatedInput("Enter year: ") { input ->
                    input.toIntOrNull()?.let { it > 0 } == true
                }

                TransactionSummary(transactions = transactions)
                    .getByYear(year)
                    .printColoredTable()
            }
            "3" -> {
                println("\nExiting application. Goodbye!".withStyle(TerminalColor.Blue))
                break
            }
            else -> println("\nInvalid option, try again.".withStyle(TerminalColor.Red))
        }
    }
}