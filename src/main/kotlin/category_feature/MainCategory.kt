package category_feature

import category_feature.feature.CategoryHelper
import category_feature.feature.CategoryManagerImpl
import category_feature.model.Operation

fun main() {
    val categoryManager = CategoryManagerImpl(listOf("food", "transport", "entertainment"))
    val categoryHelper = CategoryHelper()

    while (true) {
        println("\n=== Category List ===")
        val categories = categoryManager.getCategories().lines().filter { it.isNotBlank() }
        categories.forEach { println(it) }

        val customOptionNumber = categories.size + 1
        println("[$customOptionNumber] Custom Category")
        println("[0] Exit")

        print("Please select a category or choose 'Custom': ")
        val userInput = readlnOrNull()?.toIntOrNull()

        when {
            userInput == 0 -> {
                println("Exiting program. Goodbye!")
                return
            }

            userInput == customOptionNumber -> {
                while (true) {
                    println("\n=== Category Operations ===")
                    Operation.entries.forEachIndexed { index, operation ->
                        println("${index + 1}. ${operation.description}")
                    }

                    print("Please select an operation (1-${Operation.entries.size}): ")
                    when (readlnOrNull()?.toIntOrNull()) {
                        1 -> categoryHelper.addCategory(categoryManager)
                        2 -> categoryHelper.updateCategory(categoryManager)
                        3 -> categoryHelper.deleteCategory(categoryManager)
                        4 -> categoryHelper.checkCategory(categoryManager)
                        5 -> println("Categories:\n${categoryManager.getCategories()}")
                        6 -> {
                            println("Back to main menu.")
                            break
                        }

                        else -> println("Invalid choice. Please select a valid operation.")
                    }
                }
            }

            userInput != null && userInput in 1..categories.size -> {
                val selectedCategory = categories[userInput - 1]
                println("You selected: $selectedCategory")
            }

            else -> println("Invalid input. Please select a valid number.")
        }
    }
}
