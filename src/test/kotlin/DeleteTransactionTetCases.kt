package org.example

import org.example.feature.TransactionOperations
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

    checkDeletion(
        name = "Should return true when ID is exist",
        result = transactionOperations.deleteTransaction(UUID.randomUUID()),
        correct = true
    )

    checkDeletion(
        name = "Should return false when ID is not exist",
        result = transactionOperations.deleteTransaction(UUID.randomUUID()),
        correct = false
    )

}


