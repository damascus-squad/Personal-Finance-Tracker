package org.example

import org.example.feature.TransactionOperations
import java.time.LocalDateTime
import java.util.*

fun main(){
    val transactionOperations = TransactionOperations()

    val id= UUID.randomUUID()
    transactionOperations.addTransaction(
        Transaction(
            id =  id,
            amount = 100.0,
            date = LocalDateTime.now(),
            transactionType = TransactionType.INCOME,
            category = "Food",
            description = "Lunch"
        )
    )

    checkUpdate(
        name = "Should return true when id is exist",
        expectedResult = transactionOperations.updateTransaction(id,
            Transaction(
                id = id,
                amount = 100.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.INCOME,
                category = "Food",
                description = ""
            )
        ),
        correctResult = true,
    )

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


}

fun checkUpdate(name: String, expectedResult: Boolean, correctResult: Boolean){
    if (expectedResult == correctResult) {
    println("success - $name")
    } else
    println("failed - $name")
}