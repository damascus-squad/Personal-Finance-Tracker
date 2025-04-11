package org.example

import org.example.feature.TransactionMangerImp
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
    val transactionMangerImp = TransactionMangerImp()
    val id= UUID.randomUUID()

    transactionMangerImp.addTransaction(
        Transaction(
            id =  id,
            amount = 100.0,
            date = LocalDateTime.now(),
            transactionType = TransactionType.INCOME,
            category = Category(1, "Food"),
            description = "Lunch"
        )
    )

    checkDeletion(
        name = "Should return true when ID is exist",
        result = transactionMangerImp.deleteTransaction(id),
        correct = true
    )

    checkDeletion(
        name = "Should return false when ID is not exist",
        result = transactionMangerImp.deleteTransaction(UUID.randomUUID()),
        correct = false
    )

}