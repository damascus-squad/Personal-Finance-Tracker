package org.example

import org.example.feature.TransactionManagerImplementation
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
    val transactionManagerImplementation = TransactionManagerImplementation()

    checkList(
        name = "Should return 0 transactions before adding any transaction",
        actualSize = transactionManagerImplementation.transactions.size,
        expectedSize = 0
    )

    val id= UUID.randomUUID()
    transactionManagerImplementation.addTransaction(
        Transaction(
            id =  id,
            amount = 100.0,
            date = LocalDateTime.now(),
            transactionType = TransactionType.INCOME,
            category = Category(1,"Food"),
            description = "Lunch"
        )
    )


    check(
        name = "Should return true when adding a new transaction",
        result = transactionManagerImplementation.addTransaction(
            Transaction(
                id =  UUID.randomUUID(),
                amount = 100.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.INCOME,
                category = Category(1,"Food"),
                description = "Lunch"
            )
        ),
        correct = true,
    )


    check(
        name = "Should return false when adding existing transaction ID",
        result = transactionManagerImplementation.addTransaction(
            Transaction(
                id = id,
                amount = 150.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.EXPENSE,
                category = Category(1,"Food"),
                description = "Lunch"
            )
        ),
        correct = false,
    )

    checkList(
        name = "Should return 1 transactions after adding 1 invalid and 2 valid",
        actualSize = transactionManagerImplementation.transactions.size,
        expectedSize = 2
    )

}
