package org.example.ui

import org.example.features.category.CategoryHelper

fun runCategoriesCLI() {
    while (true) {
        println("===== Personal Finance CLI=====".withStyle(TerminalColor.Blue))
        listOf(
            "Add Category",
            "Edit Category",
            "Delete Category",
            "List All Categories",
            "Exit",
        ).forEachIndexed { index, item ->
            println("${index + 1}. $item".withStyle(TerminalColor.entries.random()))
        }
        println("==================================".withStyle(TerminalColor.Blue))
        print("Choose an option: ".withStyle(TerminalColor.Cyan))

        when (val userInput = readlnOrNull()?.toIntOrNull()) {

            1 -> {
                CategoryHelper.add()
            }

            2 -> {
                CategoryHelper.update()
            }

            3 -> {
                CategoryHelper.delete()
            }

            4 -> {
                val categories = CategoryHelper.getAll()
                categories.forEach { println("[${it.id}] ${it.name}") }
            }

            5 -> {
                println("Exiting... Goodbye!".withStyle(TerminalColor.Blue))
                return
            }

            else -> println("Invalid input. Please select a valid number.".withStyle(TerminalColor.Red))
        }
    }
}

