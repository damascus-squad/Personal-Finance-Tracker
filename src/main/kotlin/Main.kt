package org.example

import features.transaction.TransactionHelper
import org.example.ui.runCategoriesCLI
import ui.runReportsCLI
import ui.runTransactionsCLI

fun main() {
    mainCLI()
}

fun mainCLI() {
    while (true) {
        println(
            """
    ===== Personal Finance CLI=====
    1. Transactions
    2. Categories
    3. Reports
    4. Exit
    ==================================
          """.trimMargin()
        )
        print("Choose an option: ")
        when (readlnOrNull()?.trim()) {
            "1" -> runTransactionsCLI()
            "2" -> runCategoriesCLI()
            "3" -> runReportsCLI(TransactionHelper.getAll())
            "4" -> {
                println("Exiting... Goodbye!")
                return
            }

            else -> println("❌ Invalid choice!")
        }
        println("press any key to Continue")
        readlnOrNull()
    }
}