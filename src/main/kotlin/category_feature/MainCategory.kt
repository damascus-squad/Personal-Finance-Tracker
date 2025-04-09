package category_feature

import category_feature.feature.CategoryHelper
import category_feature.feature.CategoryManager
import category_feature.model.Operation


fun main() {
    val categoryManager = CategoryManager(listOf("food", "transport", "entertainment"))
    val categoryHelper = CategoryHelper()
    while (true) {
        println("\n=== Category Operations ===")
        Operation.entries.forEachIndexed { index, operation ->
            println("${index + 1}. ${operation.description}")
        }

        print("Please select an operation (1-${Operation.entries.size}): ")
        when (readlnOrNull()?.toIntOrNull()) {
            1 -> categoryHelper.selectCategory(categoryManager)
            2 -> categoryHelper.addCategory(categoryManager)
            3 -> categoryHelper.updateCategory(categoryManager)
            4 -> categoryHelper.deleteCategory(categoryManager)
            5 -> categoryHelper.checkCategory(categoryManager)
            6 -> println("Categories:\n${categoryManager.displayCategories()}")
            7 -> {
                println("Exiting program. Goodbye!")
                return
            }
            else -> println("Invalid choice. Please select a valid operation.")
        }
    }
}
