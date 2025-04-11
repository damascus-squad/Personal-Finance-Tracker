package org.example

import org.example.feature.TransactionMangerImp
import org.example.feature.categories
import java.time.LocalDateTime
import java.util.*

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
    val transaction = TransactionMangerImp()
    checkList(
        name = "Should return 0 transactions before adding any transaction",
        actualSize = transaction.transactions.size,
        expectedSize = 0
    )

    check(
        name = "Should return true when adding a new transaction",
        result = transaction.addTransaction(
            Transaction(
                id =  UUID.randomUUID(),
                amount = 100.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.INCOME,
                category = categories[0],
                description = "Lunch"
            )
        ),
        correct = true,
    )


    check(
        name = "Should return false when adding duplicate transaction ID",
        result = transaction.addTransaction(
            Transaction(
                id = UUID.randomUUID(),
                amount = 150.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.EXPENSE,
                category = categories[0],
                description = "Lunch"
            )
        ),
        correct = false,
    )


    check(
        name = "Should return false when adding transaction with negative ID",
        result = transaction.addTransaction(
            Transaction(
                id = UUID.randomUUID(),
                amount = 100.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.EXPENSE,
                category = categories[0],
                description = "Lunch"
            )
        ),
        correct = false,
    )


    check(
        name = "Should return false when adding transaction with negative amount",
        result = transaction.addTransaction(
            Transaction(
                id = UUID.randomUUID(),
                amount = -100.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.EXPENSE,
                category = categories[0],
                description = "Lunch"
            )
        ),
        correct = false,
    )

    check(
        name = "Should return false when category is blank",
        result = transaction.addTransaction(
            Transaction(
                id = UUID.randomUUID(),
                amount = 100.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.EXPENSE,
                category = categories[10],
                description = ""
            )
        ),
        correct = false,
    )

    check(
        name = "Should return false when amount is Nan",
        result = transaction.addTransaction(
            Transaction(
                id = UUID.randomUUID(),
                amount = Double.NaN,
                date = LocalDateTime.now(),
                transactionType = TransactionType.EXPENSE,
                category = categories[0],
                description = ""
            )
        ),
        correct = false,
    )

    checkList(
        name = "Should return 1 transactions after adding 4 invalid and 1 valid",
        actualSize = transaction.transactions.size,
        expectedSize = 1
    )

}
