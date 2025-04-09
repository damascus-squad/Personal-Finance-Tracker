package org.example

import org.example.feature.TransactionOperations
import java.time.LocalDateTime

fun main(){
    val transactionOperations = TransactionOperations()
    checkUpdate(
        name = "Should return false when id is not exist",
        expectedResult = transactionOperations.updateTransaction(99,
            Transaction(
                id = 99,
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
        expectedResult = transactionOperations.updateTransaction(1,
            Transaction(
                id = 1,
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
        expectedResult = transactionOperations.updateTransaction(1,
            Transaction(
                id = 1,
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
        expectedResult = transactionOperations.updateTransaction(1,
            Transaction(
                id = 1,
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
        expectedResult = transactionOperations.updateTransaction(1,
            Transaction(
                id = 1,
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
        expectedResult = transactionOperations.updateTransaction(1,
            Transaction(
                id = 1,
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