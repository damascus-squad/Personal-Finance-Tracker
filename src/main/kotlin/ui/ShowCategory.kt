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
                return
            }


//
//            customOptionNumber -> {
//                while (true) {
//                    Operation.entries.forEachIndexed { index, operation ->
//                        println("${index + 1}. ${operation.description}")
//                    }
//
//                    print("Please select an operation (1-${Operation.entries.size}): ")
//                    when (readlnOrNull()?.toIntOrNull()) {
//                        1 -> CategoryHelper.add()
//                        2 -> CategoryHelper.update()
//                        3 -> CategoryHelper.delete()
//                        4 -> CategoryHelper.checkName()
//                        5 -> {
//                            println("Categories:")
//                            categoryManager.getAll().forEach {
//                                println("[${it.id}] ${it.name}")
//                            }
//                        }                        6 -> {
//                            println("Back to main menu.")
//                            break
//                        }
//
//                        else -> println("Invalid choice. Please select a valid operation.")
//                    }
//                }
//            }

            else -> println("Invalid input. Please select a valid number.")
        }
    }
}

