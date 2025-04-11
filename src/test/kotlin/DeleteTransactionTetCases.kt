package org.example


import org.example.feature.TransactionMangerImp
import java.util.UUID

fun checkDeletion(name: String, result: Boolean, correct: Boolean) {
    if (result == correct) {
        println("success - $name")
    } else {
        println("failed - $name")
    }
}

fun main() {
    val transaction = TransactionMangerImp()
    checkDeletion(
        name = "Should return true when ID is exist",
        result = transaction.deleteTransaction(UUID.randomUUID()),
        correct = true
    )

    checkDeletion(
        name = "Should return false when ID is not exist",
        result = transaction.deleteTransaction(UUID.randomUUID()),
        correct = false
    )

    checkDeletion(
        name = "Should return false when ID is not valid",
        result = transaction.deleteTransaction(UUID.randomUUID()),
        correct = false
    )

    checkDeletion(
        name = "Should return false when ID is empty",
        result = transaction.deleteTransaction(UUID.randomUUID()),
        correct = false
    )

    checkDeletion(
        name = "Should return false when ID is blank",
        result = transaction.deleteTransaction(UUID.randomUUID()),
        correct = false
    )

}

