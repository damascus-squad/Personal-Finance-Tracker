package ui

import features.transaction.TransactionHelper.addTransaction
import features.transaction.TransactionHelper.deleteTransaction
import features.transaction.TransactionHelper.displayAllTransactions
import features.transaction.TransactionHelper.editTransaction
import features.transaction.TransactionManager

fun showTransactions(manager: TransactionManager) {
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
                println("Exit")
                return
            }

            else -> println("❌ Invalid choice!")
        }
        println("press any key to Continue")
        readlnOrNull()
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
