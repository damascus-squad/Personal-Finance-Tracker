package org.example

import categoryFeature.model.Category
import org.example.feature.TransactionMangerImpl
import util.CheckTest
import util.check
import util.checkList
import java.time.LocalDateTime
import java.util.*

class TestTransaction {
    private val transactionMangerImp = TransactionMangerImpl()

    @CheckTest
    fun checkListOfTransactionSize() {
        return transactionMangerImp.getAll().checkList(
            name = "Should return 0 transactions before adding any transaction",
            other = emptyList()
        )
    }

    @CheckTest
    fun addNewTransaction(): Boolean {
        return check(
            name = "Should return true when adding a new transaction",
            transactionMangerImp.add(
                Transaction(
                    id = UUID.randomUUID(),
                    amount = 100.0,
                    date = LocalDateTime.now(),
                    transactionType = TransactionType.INCOME,
                    category = Category(1, "Food"),
                    description = "Lunch"
                ),
            ),
            true
        )
    }

    @CheckTest
    fun updateNotExistTransaction(): Boolean {
        return check(
            name = "Should return false when adding existing transaction ID",
            result = transactionMangerImp.update(
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
    fun deleteNotExistTransaction(): Boolean {
        return check(
            name = "Should return false when ID is not exist",
            result = transactionMangerImp.delete(UUID.randomUUID()),
            false
        )
    }
}
