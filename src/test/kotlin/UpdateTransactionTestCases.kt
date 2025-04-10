package org.example

import org.example.feature.TransactionOperations
import java.time.LocalDateTime
import java.util.*

fun main(){
    val transactionOperations = TransactionOperations()
    checkUpdate(
        name = "Should return false when id is not exist",
        expectedResult = transactionOperations.updateTransaction(UUID.randomUUID(),
            Transaction(
                id = UUID.randomUUID(),
                amount = 100.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.INCOME,
                category = "Food",
                description = ""
            )
        ),
        correctResult = false,
    )

    checkUpdate(
        name = "Should return false when amount is non positive",
        expectedResult = transactionOperations.updateTransaction(UUID.randomUUID(),
            Transaction(
                id = UUID.randomUUID(),
                amount = -100.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.INCOME,
                category = "Food",
                description = ""
            )
        ),
        correctResult = false,
    )

    checkUpdate(
        name = "Should return false when category is not in the list",
        expectedResult = transactionOperations.updateTransaction(UUID.randomUUID(),
            Transaction(
                id = UUID.randomUUID(),
                amount = -100.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.INCOME,
                category = "Food",
                description = ""
            )
        ),
        correctResult = false,
    )

    checkUpdate(
        name = "Should return false when transactionType valid",
        expectedResult = transactionOperations.updateTransaction(UUID.randomUUID(),
            Transaction(
                id = UUID.randomUUID(),
                amount = -100.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.INCOME,
                category = "Food",
                description = ""
            )
        ),
        correctResult = true
    )

    checkUpdate(
        name = "Should return false when category is not in Category list",
        expectedResult = transactionOperations.updateTransaction(UUID.randomUUID(),
            Transaction(
                id = UUID.randomUUID(),
                amount = -100.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.INCOME,
                category = "Food",
                description = ""
            )
        ),
        correctResult = true
    )

    checkUpdate(
        name = "Should return false when date is valid",
        expectedResult = transactionOperations.updateTransaction(UUID.randomUUID(),
            Transaction(
                id = UUID.randomUUID(),
                amount = -100.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.INCOME,
                category = "Food",
                description = ""
            )
        ),
        correctResult = false
    )

}

fun checkUpdate(name: String, expectedResult: Boolean, correctResult: Boolean){
    if (expectedResult == correctResult) {
    println("success - $name")
    } else
    println("failed - $name")
}