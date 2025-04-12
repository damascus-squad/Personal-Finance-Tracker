package org.example

import features.transaction.TransactionHelper
import org.example.ui.TerminalColor
import org.example.ui.clearConsole
import org.example.ui.enableWindowsAnsi
import org.example.ui.runCategoriesCLI
import org.example.ui.withStyle
import ui.runReportsCLI
import ui.runTransactionsCLI

fun main() {
    enableWindowsAnsi()
    mainCLI()
}

fun mainCLI() {
    while (true) {
        clearConsole()
        println("===== Personal Finance CLI=====".withStyle(TerminalColor.Blue))
        listOf(
            "Transactions",
            "Categories",
            "Reports",
            "Exit"
        ).forEachIndexed { index, item ->
            println("${index + 1}. $item".withStyle(TerminalColor.entries.random()))
        }
        println("==================================".withStyle(TerminalColor.Blue))
        print("Choose an option: ".withStyle(TerminalColor.Cyan))
        when (readlnOrNull()?.trim()) {
            "1" -> runTransactionsCLI()
            "2" -> runCategoriesCLI()
            "3" -> runReportsCLI(TransactionHelper.getAll())
            "4" -> {
                println("Exiting... Goodbye!".withStyle(TerminalColor.Blue))
                return
            }

            else -> println("❌ Invalid choice!".withStyle(TerminalColor.Red))
        }
        println("press any key to Continue")
        readlnOrNull()
    }
}