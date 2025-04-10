package org.example.feature

import org.example.Transaction
import org.example.TransactionType
import java.time.LocalDateTime

fun runCLI(service: TransactionService){
    while (true) {
        println(
            """===== Personal Finance CLI=====
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
            "4" -> getAllTransactions(service)
            "5" -> {
                println("Exiting... Goodbye!")
                return
            }
            else -> println("❌ Invalid choice!")
        }
    }

}

fun addTransaction(service: TransactionService) {
    val id = 1

    var amount: Double? = null
    print("Enter amount: ")
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
        id = id,
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
    println("Choose transaction to update: ")
    getAllTransactions(service)

    val id = readlnOrNull()?.toIntOrNull() ?: return println("❌ Invalid ID.")

    val existing = service.getTransactionById(id)
    if (existing == null) {
        println("❌ Transaction not found.")
        return
    }

    print("Enter new amount: ")
    val amount = readlnOrNull()?.toDoubleOrNull()
    if (amount == null || amount <= 0) {
        println("❌ Invalid amount.")
        return
    }

    print("Enter new category: ")
    val category = readlnOrNull().orEmpty()
    if (category.isBlank()) {
        println("❌ Category is required.")
        return
    }

    print("Enter new description (optional): ")
    val description = readlnOrNull()

    val updated = existing.copy(
        amount = amount,
        category = category,
        description = description
    )

    if (service.updateTransaction(id, updated)) {
        println("✅ Transaction updated.")
    } else {
        println("❌ Failed to update transaction.")
    }
}

fun deleteTransaction(service: TransactionService) {
    print("Enter ID to delete: ")
    val id = readLine()?.toIntOrNull() ?: return println("❌ Invalid ID.")

    if (service.deleteTransaction(id)) {
        println("✅ Transaction deleted.")
    } else {
        println("❌ Transaction not found.")
    }
}

fun getAllTransactions(service: TransactionService) {
    val transactions = service.getAllTransactions()
    if (transactions.isEmpty()) {
        println("No transactions found.")
        return
    }

    println("=== All Transactions ===")
    for (t in transactions) {
        println(" $t. | Amount: ${t.amount} | Type: ${t.transactionType} | Category: ${t.category} | Date: ${t.date} | Desc: ${t.description}")
    }
}
