package org.example.feature
import org.example.Transaction
import org.example.TransactionType
import java.time.LocalDateTime
import java.util.*


fun runCLI(service: TransactionService){
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
            "1" -> addTransaction(service)
            "2" -> editTransaction(service)
            "3" -> deleteTransaction(service)
            "4" -> displayAllTransactions(service)
            "5" -> {
                println("Exiting... Goodbye!")
                return
            }
            else -> println("❌ Invalid choice!")
        }



    }
    }


fun addTransaction(service: TransactionService) {
    var amount: Double? = null
    while (amount == null || amount <= 0) {
        print("Enter amount (must be positive): ")
        amount = readlnOrNull()?.toDoubleOrNull()
        if (amount == null || amount <= 0) {
            println("❌ Invalid amount. Please enter a positive number.")
        }
    }

    var transactionType:TransactionType? = null
    while (transactionType == null) {
        println("Select transaction type:")
        println("1. INCOME")
        println("2. EXPENSE")
        print("Enter choice (1 or 2): ")
        when (readlnOrNull()?.trim()) {
            "1" -> transactionType = TransactionType.INCOME
            "2" -> transactionType = TransactionType.EXPENSE
            else -> println("❌ Invalid choice. Please select 1 or 2.")
        }
    }

   print("Enter category: ")
    var category = readln()
    while (category.isBlank()) {
        println("❌ Category is required. Please Enter Category")
        category = readln()
    }

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

    if (service.addTransaction(transaction)) {
        println("✅ Transaction added.")
    } else {
        println("❌ Failed to add transaction.")
    }
}

fun editTransaction(service: TransactionService) {
    val transactions = service.getAllTransactions()
    if (transactions.isEmpty()) {
        println("❌ No transactions found.")
        println("Select Your option Again ")
        return
    }
    displayAllTransactions(service)

    println("Enter the number of the transaction you want to edit:")
    var index = readlnOrNull()?.toIntOrNull()?.minus(1)

    while (index == null || index !in transactions.indices) {
        println("❌ Invalid selection. Please Enter Number from the selection list ")
        index = readlnOrNull()?.toIntOrNull()?.minus(1)
    }
    val existingTransaction = transactions[index]
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
                print("Enter new amount: ")
                val newAmount = readlnOrNull()?.toDoubleOrNull()
                if (newAmount != null && newAmount > 0) {
                    updated = updated.copy(amount = newAmount)
                    service.updateTransaction(existingTransaction.id, updated)
                    println("✅ Amount updated.")
                } else println("❌ Invalid amount.")
            }

            2 -> {
                println("Select transaction type:")
                println("1. INCOME")
                println("2. EXPENSE")
                when (readlnOrNull()?.trim()) {
                    "1" -> {
                        updated = updated.copy(transactionType = TransactionType.INCOME)
                        service.updateTransaction(existingTransaction.id, updated)
                        println("✅ Transaction type updated.")
                    }
                    "2" -> {
                        updated = updated.copy(transactionType = TransactionType.EXPENSE)
                        service.updateTransaction(existingTransaction.id, updated)
                        println("✅ Transaction type updated.")
                    }
                    else -> println("❌ Invalid type.")
                }
            }
            3 -> {
                print("Enter new category: ")
                val newCategory = readlnOrNull()?.trim()
                if (!newCategory.isNullOrEmpty()) {
                    updated = updated.copy(category = newCategory)
                    service.updateTransaction(existingTransaction.id, updated)
                    println("✅ Category updated.")
                } else println("❌ Invalid category.")
            }
            4 -> {
                print("Enter new description: ")
                val newDescription = readlnOrNull()?.trim()
                updated = updated.copy(description = newDescription)
                service.updateTransaction(existingTransaction.id, updated)
                println("✅ Description updated.")
            }
            5 -> {
                println("Transaction editing finished.")
                break
            }

            else -> println("❌ Invalid choice, try again")


        }
    }
}


fun deleteTransaction(service: TransactionService) {
    val transactions = service.getAllTransactions()

    if (transactions.isEmpty()) {
        println("❌ No transactions found.")
        println("Select Your option Again ")
        return
    }

    displayAllTransactions(service)
    println("Enter the number of the Transaction you want to Delete:")
    var index = readlnOrNull()?.toIntOrNull()?.minus(1)

    while (index == null || index !in transactions.indices) {
        println("❌ Invalid selection. Please Enter Number from the selection list ")
        index = readlnOrNull()?.toIntOrNull()?.minus(1)
    }

    val existingTransaction = transactions[index]

    if (service.deleteTransaction(existingTransaction.id)) {
        println("✅ Transaction deleted.")
    } else {
        println("❌ Transaction not found.")
    }
}




fun displayAllTransactions(service: TransactionService) {
    val transactions = service.getAllTransactions()
    if (transactions.isEmpty()) {
        println("No transactions found.")
        return
    }
    println("=== All Transactions ===")
    transactions.forEachIndexed {index, transaction->
        println("${index+1}. ID: ${transaction.id}. | Amount: ${transaction.amount} | Type: ${transaction.transactionType} | Category: ${transaction.category} | Date: ${transaction.date} | Desc: ${transaction.description}")
    }
}
