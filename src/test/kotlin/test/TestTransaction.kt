package org.example

import org.example.model.Category
import org.example.features.feature.TransactionMangerImp
import util.CheckTest
import util.check
import util.checkList
import java.time.LocalDateTime
import java.util.*

class TestTransaction {
    private val transactionMangerImp = TransactionMangerImp()
    @CheckTest
    fun checkListOfTransactionSize() {
        return transactionMangerImp.transactions.checkList(
            name = "Should return 0 transactions before adding any transaction",
            other = emptyList()
        )
    }

    @CheckTest
    fun addNewTransaction():Boolean{
        return check(
            name = "Should return true when adding a new transaction",
            transactionMangerImp.addTransaction(Transaction(
                id =  UUID.randomUUID(),
                amount = 100.0,
                date = LocalDateTime.now(),
                transactionType = TransactionType.INCOME,
                category = Category(1,"Food"),
                description = "Lunch"),),
            true)
    }

    @CheckTest
    fun updateNotExistTransaction():Boolean {
        return check(
            name = "Should return false when adding existing transaction ID",
            result = transactionMangerImp.updateTransaction(
                UUID.randomUUID(),
                Transaction(
                    id = UUID.randomUUID(),
                    amount = 150.0,
                    date = LocalDateTime.now(),
                    transactionType = TransactionType.EXPENSE,
                    category = Category(1, "Food"),
                    description = "Lunch"
                )
            ),
            false,
        )
    }

    @CheckTest
    fun deleteNotExistTransaction():Boolean{
        return check(
            name = "Should return false when ID is not exist",
            result = transactionMangerImp.deleteTransaction(UUID.randomUUID()),
            false
        )
    }
}
