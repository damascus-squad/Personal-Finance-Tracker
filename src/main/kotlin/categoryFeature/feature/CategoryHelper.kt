package categoryFeature.feature

import categoryFeature.model.Category

object CategoryHelper {


    fun addCategory(manager: CategoryManager) {
        print("Enter category name to add: ")
        val categoryName = readlnOrNull()?.trim()
        if (!categoryName.isNullOrEmpty()) {
            if (!isValidCategoryName(categoryName)) {
                println("Invalid name. Only letters are allowed, no numbers or symbols.")
                return
            }

            val result = manager.add(categoryName)
            println(if (result) "Category '$categoryName' added successfully." else "Category already exists.")
        } else {
            println("Invalid category name.")
        }
    }

    fun updateCategory(manager: CategoryManager) {
        val categories = manager.getCategories()
        categories.forEach { println("[${it.id}] ${it.name}") }

        print("Enter the category ID to update: ")
        val idToUpdate = readlnOrNull()?.toIntOrNull()

        if (idToUpdate == null) {
            println("Invalid input. Please enter a valid number.")
            return
        }

        val oldCategory = manager.getCategoryById(idToUpdate)
        if (oldCategory == null) {
            println("Category with ID $idToUpdate not found.")
            return
        }

        print("Enter new category name: ")
        val newName = readlnOrNull()?.trim()

        if (!newName.isNullOrEmpty()) {
            if (!isValidCategoryName(newName)) {
                println("Invalid new name. Only letters are allowed, no numbers or symbols.")
                return
            }

            if (manager.checkExists(newName)) {
                println("Error: '$newName' already exists.")
            } else {
                val result = manager.update(idToUpdate, newName)
                println(if (result) "Updated successfully." else "Update failed.")
            }
        } else {
            println("Invalid input.")
        }
    }

    fun deleteCategory(manager: CategoryManager) {
        val categories = manager.getCategories()
        categories.forEach { println("[${it.id}] ${it.name}") }

        print("Enter the category ID to delete: ")
        val idToDelete = readlnOrNull()?.toIntOrNull()

        if (idToDelete == null) {
            println("Invalid input. Please enter a valid number.")
            return
        }

        val result = manager.delete(idToDelete)
        println(if (result) "Category deleted." else "Category with ID $idToDelete not found.")
    }

    fun checkCategory(manager: CategoryManagerImpl) {
        print("Enter category name to check: ")
        val name = readlnOrNull()?.trim()
        if (!name.isNullOrEmpty()) {
            println(if (manager.checkExists(name)) "Category exists." else "Category does not exist.")
        } else {
            println("Invalid input.")
        }
    }

    fun selectCategory(manager: CategoryManager): Category {
        val categories = manager.getCategories()

        if (categories.isEmpty()) {
            println("⚠️ No categories found. Please add a category.")
            addCategory(manager)
            val newCategory = manager.getCategories().last()
            println("✅ You added: ${newCategory.name}")
            return newCategory
        }

        println("\n📋 All Categories:")
        categories.forEach { category ->
            println("${category.id} - ${category.name}")
        }
        println("${categories.size+1} - Custom Category")

        println("🔽 Select a category by entering its number, or Enter Custom Category")
        var selectedId = readlnOrNull()?.toIntOrNull()

        while (selectedId == null || selectedId > categories.size+1) {
            println("❌ Invalid selection. Please enter a valid category number:")
            selectedId = readlnOrNull()?.toIntOrNull()
        }
        if (selectedId == (categories.size+1)) addCategory(manager)

        val selectedCategory = manager.getCategoryById(selectedId)!!
        println("✅ You selected: ${selectedCategory.name}")
        return selectedCategory
    }

    private fun isValidCategoryName(name: String): Boolean {
        val regex = Regex("^[\\p{L}\\s]+$")
        return regex.matches(name)
    }

}
