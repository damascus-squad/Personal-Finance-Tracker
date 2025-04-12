package org.example.ui

import org.example.features.category.CategoryHelper

fun runCategoriesCLI() {
    while (true) {

        println("\n=== Category Operations ===")
        println(
            """
1. Add Category
2. Edit Category
3. Delete Category
4. List All Categories
5. Exit
==================================
          """.trimMargin()
        )


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

