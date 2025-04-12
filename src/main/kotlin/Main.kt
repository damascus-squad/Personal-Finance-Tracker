package org.example

import org.example.feature.*

fun main() {
    runCLI()
}
fun runCLI() {
    while (true) {
        println(
            """
    ===== Personal Finance CLI=====
    1. Add Transaction
    2. Edit Transaction
    3. Delete Transaction
    4. List All Transactions
    5. Exit
    ==================================
          """.trimMargin()
        )
        print("Choose an option: ")
        when (readlnOrNull()?.trim()) {
            "1" -> TransactionHelper.add()
            "2" -> TransactionHelper.edit()
            "3" -> TransactionHelper.delete()
            "4" -> TransactionHelper.displayAll()
            "5" -> {
                println("Exiting... Goodbye!")
                return
            }

            else -> println("❌ Invalid choice!")
        }
        println("press any key to Continue")
        readlnOrNull()
    }
}