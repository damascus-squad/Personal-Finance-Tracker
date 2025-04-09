package org.example

import org.example.feature.TransactionOperations
import java.time.LocalDateTime

fun check(name: String, result: Boolean, correct: Boolean) {
    if (result == correct) {
        println("success - $name")
    } else {
        println("failed - $name")
    }
}

fun checkList(name: String, actualSize: Int, expectedSize: Int) {
    if (actualSize == expectedSize) {
        println("success - $name | found: $actualSize transactions")
    } else {
        println("failed - ($name)... it should return $expectedSize transactions but found: $actualSize")
    }
}

fun main() {
    val transactionOperations = TransactionOperations()

    checkList(
        name = "Should return 0 transactions before adding any transaction",
        actualSize = transactionOperations.transactions.size,
        expectedSize = 0
    )

    check(
        name = "Should return true when adding a new transaction",
        result = transactionOperations.addTransaction(
            Transaction(
                id = 1,
                amount = 100.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.INCOME,
                category = "Food",
                description = "Lunch"
            )
        ),
        correct = true,
    )


    check(
        name = "Should return false when adding duplicate transaction ID",
        result = transactionOperations.addTransaction(
            Transaction(
                id = 1,
                amount = 150.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.EXPENSE,
                category = "Food",
                description = "Lunch"
            )
        ),
        correct = false,
    )


    check(
        name = "Should return false when adding transaction with negative ID",
        result = transactionOperations.addTransaction(
            Transaction(
                id = -1,
                amount = 100.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.EXPENSE,
                category = "Food",
                description = "Lunch"
            )
        ),
        correct = false,
    )


    check(
        name = "Should return false when adding transaction with negative amount",
        result = transactionOperations.addTransaction(
            Transaction(
                id = 4,
                amount = -100.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.EXPENSE,
                category = "Food",
                description = "Lunch"
            )
        ),
        correct = false,
    )

    check(
        name = "Should return false when category is blank",
        result = transactionOperations.addTransaction(
            Transaction(
                id = 5,
                amount = 100.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.EXPENSE,
                category = "",
                description = ""
            )
        ),
        correct = false,
    )

    check(
        name = "Should return false when amount is Nan",
        result = transactionOperations.addTransaction(
            Transaction(
                id = 5,
                amount = Double.NaN,
                date = LocalDateTime.now(),
                transactionType = TransactionType.EXPENSE,
                category = "Food",
                description = ""
            )
        ),
        correct = false,
    )

    checkList(
        name = "Should return 1 transactions after adding 4 invalid and 1 valid",
        actualSize = transactionOperations.transactions.size,
        expectedSize = 1
    )

}
