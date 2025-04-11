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
        result = transactionOperations.deleteTransaction("1"),
        correct = true
    )


    checkDeletion(
        name = "Should return false when ID is not exist",
        result = transactionOperations.deleteTransaction("999"),
        correct = false
    )


    checkDeletion(
        name = "Should return false when ID is not valid",
        result = transactionOperations.deleteTransaction("mT77"),
        correct = false
    )


    checkDeletion(
        name = "Should return false when ID is empty",
        result = transactionOperations.deleteTransaction(""),
        correct = false
    )


    checkDeletion(
        name = "Should return false when ID is blank",
        result = transactionOperations.deleteTransaction("    "),
        correct = false
    )




}



fun deleteTransaction(ID: String): Boolean {
    if (ID.isBlank() || !ID.all { it.isDigit() } ) {return false}


    val transaction = transactions.find { it.id == ID.toInt() } ?: return false
    return transactions.remove(transaction)
}

