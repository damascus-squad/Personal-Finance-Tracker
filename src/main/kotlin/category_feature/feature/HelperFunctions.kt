package category_feature.feature

class CategoryHelper {

    fun selectCategory(manager: CategoryManager) {
        val categories = manager.displayCategories().lines().filter { it.isNotBlank() }
        categories.forEach { println(it) }

        print("Enter the category number to select: ")
        val categoryNumber = readlnOrNull()?.toIntOrNull()

        if (categoryNumber != null && categoryNumber in 1..categories.size) {
            println("You selected: ${categories[categoryNumber - 1]}")
        } else {
            println("Invalid category number.")
        }
    }

    fun addCategory(manager: CategoryManager) {
        print("Enter category name to add: ")
        val categoryName = readlnOrNull()?.trim()
        if (!categoryName.isNullOrEmpty()) {
            if (!isValidCategoryName(categoryName)) {
                println("Invalid name. Only letters are allowed, no numbers or symbols.")
                return
            }

            val result = manager.addCategory(categoryName)
            println(if (result) "Category '$categoryName' added successfully." else "Category already exists.")
        } else {
            println("Invalid category name.")
        }
    }


    fun updateCategory(manager: CategoryManager) {
        print("Enter old category name: ")
        val oldName = readlnOrNull()?.trim()
        print("Enter new category name: ")
        val newName = readlnOrNull()?.trim()

        if (!oldName.isNullOrEmpty() && !newName.isNullOrEmpty()) {
            if (!isValidCategoryName(newName)) {
                println("Invalid new name. Only letters are allowed, no numbers or symbols.")
                return
            }

            when {
                !manager.checkExists(oldName) -> println("Error: '$oldName' does not exist.")
                manager.checkExists(newName) -> println("Error: '$newName' already exists.")
                else -> {
                    manager.updateCategory(oldName, newName)
                    println("Updated successfully.")
                }
            }
        } else {
            println("Invalid input.")
        }
    }


    fun deleteCategory(manager: CategoryManager) {
        print("Enter category name to delete: ")
        val name = readlnOrNull()?.trim()
        if (!name.isNullOrEmpty()) {
            val result = manager.deleteCategory(name)
            println(if (result) "Category deleted." else "Category not found.")
        } else {
            println("Invalid category name.")
        }
    }

    fun checkCategory(manager: CategoryManager) {
        print("Enter category name to check: ")
        val name = readlnOrNull()?.trim()
        if (!name.isNullOrEmpty()) {
            println(if (manager.checkExists(name)) "Category exists." else "Category does not exist.")
        } else {
            println("Invalid input.")
        }
    }

    private fun isValidCategoryName(name: String): Boolean {
        val regex = Regex("^[\\p{L}\\s]+$")
        return regex.matches(name)
    }
}
