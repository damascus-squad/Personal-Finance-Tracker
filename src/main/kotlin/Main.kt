package org.example

import org.example.feature.*
import org.example.feature.TransactionHelper.addTransaction
import org.example.feature.TransactionHelper.deleteTransaction
import org.example.feature.TransactionHelper.displayAllTransactions
import org.example.feature.TransactionHelper.editTransaction

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
    5. Exit
    ==================================
          """.trimMargin()
        )
        print("Choose an option: ")
        when (readlnOrNull()?.trim()) {
            "1" -> addTransaction(manager)
            "2" -> editTransaction(manager)
            "3" -> deleteTransaction(manager)
            "4" -> displayAllTransactions(manager)
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