package org.example

import org.example.feature.TransactionManagerImplementation
import java.time.LocalDateTime
import java.util.*

fun main(){
    val transactionManagerImplementation = TransactionManagerImplementation()

    val id= UUID.randomUUID()
    transactionManagerImplementation.addTransaction(
        Transaction(
            id =  id,
            amount = 100.0,
            date = LocalDateTime.now(),
            transactionType = TransactionType.INCOME,
            category = Category(1,"Food")   ,
            description = "Lunch"
        )
    )

    checkUpdate(
        name = "Should return true when id is exist",
        expectedResult = transactionManagerImplementation.updateTransaction(id,
            Transaction(
                id = id,
                amount = 100.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.INCOME,
                category = Category(1,"Food"),
                description = ""
            )
        ),
        correctResult = true,
    )

    checkUpdate(
        name = "Should return false when id is not exist",
        expectedResult = transactionManagerImplementation.updateTransaction(UUID.randomUUID(),
            Transaction(
                id = UUID.randomUUID(),
                amount = 100.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.INCOME,
                category = Category(1,"Food"),
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