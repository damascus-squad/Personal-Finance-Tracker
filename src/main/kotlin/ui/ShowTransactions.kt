package ui

import features.transaction.TransactionHelper

fun runTransactionsCLI() {
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
