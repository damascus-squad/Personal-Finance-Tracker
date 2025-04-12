package features.transaction

import model.Transaction
import model.TransactionType
import org.example.features.category.CategoryHelper
import org.example.storage.FileStorageFactory
import org.example.ui.printColoredTable
import java.time.LocalDateTime
import java.util.*

object TransactionHelper {
    private val transactionManager: TransactionManager = TransactionMangerImpl(
        storage = FileStorageFactory.create("transactions.json")
    )

    fun add() {
        val amount = readAmount()
        val transactionType = selectTransactionType()
        val category = CategoryHelper.select()

        print("Enter description (optional): ")
        val description = readlnOrNull()

        val transaction = Transaction(
            id = UUID.randomUUID(),
            amount = amount,
            transactionType = transactionType,
            date = LocalDateTime.now(),
            category = category,
            description = description
        )

        if (transactionManager.add(transaction)) {
            println("✅ Transaction added.")
        } else {
            println("❌ Failed to add transaction.")
        }
    }

    fun edit() {
        val transactions = transactionManager.getAll()
        if (transactions.isEmpty()) {
            println("❌ No transactions found.")
            println("Select Your option Again ")
            return
        }
        displayAll()

        println("Enter the number of the transaction you want to edit:")
        val existingTransaction = transactions[validateIndexWithinRange(transactions.size)]
        var updatedTransaction = existingTransaction.copy()
        var choice: Int? = null
        while (true) {
            println(
                """
          Choose the field you want to edit:
          1. Amount
          2. Transaction Type
          3. Category
          4. Description
          5. finish Editing
      """.trimIndent()
            )

            choice = readlnOrNull()?.toIntOrNull()

            when (choice) {
                1 -> {
                    updatedTransaction = updatedTransaction.copy(amount = readAmount())
                    updateTransactionField(existingTransaction, updatedTransaction)
                }

                2 -> {
                    updatedTransaction = updatedTransaction.copy(transactionType = selectTransactionType())
                    updateTransactionField(existingTransaction, updatedTransaction)
                }

                3 -> {
                    updatedTransaction = updatedTransaction.copy(category = CategoryHelper.select())
                    updateTransactionField(existingTransaction, updatedTransaction)
                }

                4 -> {
                    print("Enter new description: ")
                    val newDescription = readlnOrNull()?.trim()
                    updatedTransaction = updatedTransaction.copy(description = newDescription)
                    updateTransactionField(existingTransaction, updatedTransaction)
                }

                5 -> {
                    println("Transaction editing finished.")
                    break
                }

                else -> println("❌ Invalid choice, try again")
            }
        }
    }

    fun delete() {
        val transactions = transactionManager.getAll()

        if (transactions.isEmpty()) {
            println("❌ No transactions found.")
            println("Select Your option Again ")
            return
        }

        displayAll()
        println("Enter the number of the Transaction you want to Delete:")
        val index = validateIndexWithinRange(transactions.size)

        val existingTransaction = transactions[index]

        if (transactionManager.delete(existingTransaction.id)) {
            println("✅ Transaction deleted.")
        } else {
            println("❌ Transaction not found.")
        }
    }

    fun displayAll() {
        val transactions = transactionManager.getAll()
        if (transactions.isEmpty()) {
            println("No transactions found.")
            return
        }
        println("=== All Transactions ===")
        transactions.printColoredTable()
    }

    fun getAll(): List<Transaction> {
        return transactionManager.getAll()
    }

    private fun readAmount(): Double {
        var amount: Double?
        do {
            print("Enter amount (must be positive): ")
            amount = readlnOrNull()?.toDoubleOrNull()
            if (amount == null || amount <= 0) {
                println("❌ Invalid amount. Please enter a positive number.")
            }
        } while (amount == null || amount <= 0)

        return amount
    }

    private fun selectTransactionType(): TransactionType {
        while (true) {
            println("Select transaction type:")
            println("1. INCOME")
            println("2. EXPENSE")
            when (readlnOrNull()?.trim()) {
                "1" -> return TransactionType.INCOME
                "2" -> return TransactionType.EXPENSE
                else -> println("❌ Invalid choice. Please select 1 or 2.")
            }
        }
    }

    private fun validateIndexWithinRange(size: Int): Int {
        var index = readlnOrNull()?.toIntOrNull()?.minus(1)
        while (index == null || index !in 0 until size) {
            print("❌ Invalid selection. Please try again: ")
            index = readlnOrNull()?.toIntOrNull()?.minus(1)
        }
        return index
    }

    private fun updateTransactionField(
        existingTransaction: Transaction,
        updatedTransaction: Transaction
    ) {
        if (transactionManager.update(existingTransaction.id, updatedTransaction)) {
            println("✅ Transaction updated.")
        } else {
            println("❌ Failed to update transaction.")
        }
    }

}