package categoryFeature

import categoryFeature.feature.CategoryHelper
import categoryFeature.feature.CategoryManagerImpl
import categoryFeature.model.Operation

fun main() {
//    while (true) {
//        println("\n=== Category List ===")
//        val categories = CategoryHelper
//        categories.forEach { println("[${it.id}] ${it.name}") }
//
//        val customOptionNumber = categories.size + 1
//        println("[$customOptionNumber] Custom Category")
//        println("[0] Exit")
//
//        print("Please select a category or choose 'Custom': ")
//        val userInput = readlnOrNull()?.toIntOrNull()
//
//        when (userInput) {
//            0 -> {
//                println("Exiting program. Goodbye!")
//                return
//            }
//
//            in 1..categories.size -> {
//                val selectedCategory = categories[userInput?.minus(1)!!]
//                println("You selected: ${selectedCategory.name}")
//            }
//
//            customOptionNumber -> {
//                while (true) {
//                    println("\n=== Category Operations ===")
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
//
//            else -> println("Invalid input. Please select a valid number.")
//        }
//    }
}
