package org.example

import org.example.feature.TransactionOperations
import java.time.LocalDateTime
import java.util.UUID


fun checkDeletion(name: String, result: Boolean, correct: Boolean) {
    if (result == correct) {
        println("success - $name")
    } else {
        println("failed - $name")
    }
}

fun main() {
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

    checkDeletion(
        name = "Should return true when ID is exist",
        result = transactionOperations.deleteTransaction(id),
        correct = true
    )

    checkDeletion(
        name = "Should return false when ID is not exist",
        result = transactionOperations.deleteTransaction(UUID.randomUUID()),
        correct = false
    )

}


