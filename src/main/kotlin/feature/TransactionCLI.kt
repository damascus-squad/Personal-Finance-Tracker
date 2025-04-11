    package org.example.feature

    import org.example.Category
    import org.example.Transaction
    import org.example.TransactionType
    import java.time.LocalDateTime
    import java.util.*

    val categories = listOf(
        Category(1, "Food"),
        Category(2, "Transport"),
        Category(3, "Entertainment"),
        Category(4, "Bills"),
        Category(5, "Shopping"),
        Category(5, "Health"),
        Category(6, "Salary"),
        Category(7, "Other"),
        Category(8, "Custom")
    )

    fun runCLI(manager: TransactionManager) {
        while (true) {
            println(
                """
    ===== Personal Finance CLI=====
    1. Add Transaction
    2. Edit Transaction
    3. Delete Transaction
    4. List All Transactions
    5. Exit
    ==================================
          """.trimMargin()
            )
            print("Choose an option: ")
            when (readlnOrNull()?.trim()) {
                "1" -> addTransaction(manager)
                "2" -> editTransaction(manager)
                "3" -> deleteTransaction(manager)
                "4" -> displayAllTransactions(manager)
                "5" -> {
                    println("Exiting... Goodbye!")
                    return
                }

                else -> println("❌ Invalid choice!")
            }
            println("press any key to Continue")
            readlnOrNull()
        }
    }


    fun addTransaction(manager: TransactionManager) {
        val amount = readPositiveAmount()
        val transactionType = selectTransactionType()
        val category = selectCategory()

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

        if (manager.addTransaction(transaction)) {
            println("✅ Transaction added.")
        } else {
            println("❌ Failed to add transaction.")
        }
    }

    fun editTransaction(manager: TransactionManager) {
        val transactions = manager.getAllTransactions()
        if (transactions.isEmpty()) {
            println("❌ No transactions found.")
            println("Select Your option Again ")
            return
        }
        displayAllTransactions(manager)

        println("Enter the number of the transaction you want to edit:")
        val existingTransaction = transactions[validateIndexWithinRange(transactions.size)]
        var updated = existingTransaction.copy()
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
                    updated = updated.copy(amount = readPositiveAmount())
                    updateTransactionField(manager, existingTransaction, updated)
                }
                2 -> {
                    updated = updated.copy(transactionType = selectTransactionType())
                    updateTransactionField(manager, existingTransaction, updated)
                }
                3 -> {
                    val category = selectCategory()
                    updated = updated.copy(category = category)
                    updateTransactionField(manager, existingTransaction, updated)
                }
                4 -> {
                    print("Enter new description: ")
                    val newDescription = readlnOrNull()?.trim()
                    updated = updated.copy(description = newDescription)
                    updateTransactionField(manager, existingTransaction, updated)
                }
                5 -> {
                    println("Transaction editing finished.")
                    break
                }

                else -> println("❌ Invalid choice, try again")
            }
        }
    }


    fun deleteTransaction(manager: TransactionManager) {
        val transactions = manager.getAllTransactions()

        if (transactions.isEmpty()) {
            println("❌ No transactions found.")
            println("Select Your option Again ")
            return
        }

        displayAllTransactions(manager)
        println("Enter the number of the Transaction you want to Delete:")
        val index = validateIndexWithinRange(transactions.size)

        val existingTransaction = transactions[index]

        if (manager.deleteTransaction(existingTransaction.id)) {
            println("✅ Transaction deleted.")
        } else {
            println("❌ Transaction not found.")
        }
    }


    fun displayAllTransactions(manager: TransactionManager) {
        val transactions = manager.getAllTransactions()
        if (transactions.isEmpty()) {
            println("No transactions found.")
            return
        }
        println("=== All Transactions ===")
        transactions.forEachIndexed { index, transaction ->
            println("${index + 1}. ID: ${transaction.id}. | Amount: ${transaction.amount} | Type: ${transaction.transactionType} | Category: ${transaction.category.name} | Date: ${transaction.date} | Desc: ${transaction.description}")
        }
    }


    fun readPositiveAmount(): Double {
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

    fun selectCategory(): Category {
        println("List of categories: ")
        categories.forEachIndexed { index, category ->
            println("${index + 1}. ${category.name}")
        }
        return categories[validateIndexWithinRange(categories.size)]
    }

    fun selectTransactionType(): TransactionType {
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

    fun validateIndexWithinRange(size: Int): Int {
        var index = readlnOrNull()?.toIntOrNull()?.minus(1)
        while (index == null || index !in 0 until size) {
            print("❌ Invalid selection. Please try again: ")
            index = readlnOrNull()?.toIntOrNull()?.minus(1)
        }
        return index
    }

    fun updateTransactionField(
        manager: TransactionManager,
        existingTransaction: Transaction,
        updatedTransaction: Transaction
    ) {
        if (manager.updateTransaction(existingTransaction.id, updatedTransaction)) {
            println("✅ Transaction updated.")
        } else {
            println("❌ Failed to update transaction.")
        }
    }