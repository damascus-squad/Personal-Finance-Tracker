package org.example

import features.transaction.TransactionManager
import features.transaction.TransactionMangerImp
import ui.showCategories
import ui.showReports
import ui.showTransactions

fun main() {
    val manger = TransactionMangerImp()
    transactionCLI(manger)
}


fun transactionCLI(manager: TransactionManager) {
    while (true) {
        println(
            """
    ===== Personal Finance CLI=====
    1. Transaction
    2. Categories
    3. Reports
    4. Exit
    ==================================
          """.trimMargin()
        )
        print("Choose an option: ")
        when (readlnOrNull()?.trim()) {
            "1" -> showTransactions(manager)
            "2" -> showCategories()
            "3" -> showReports(manager.getAllTransactions())
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