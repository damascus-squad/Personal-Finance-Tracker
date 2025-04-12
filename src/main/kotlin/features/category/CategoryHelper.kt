package org.example.features.category

import features.category.CategoryManagerImpl
import org.example.model.Category
import org.example.storage.FileStorageFactory

object CategoryHelper {
    private val categoryManager: CategoryManager = CategoryManagerImpl(
        listOf("Food", "Transport", "Entertainment"),
        storage = FileStorageFactory.create("categories.json")
    )


    fun add() {
        print("Enter category name to add: ")
        val categoryName = readlnOrNull()?.trim()
        if (!categoryName.isNullOrEmpty()) {
            if (!isValidCategoryName(categoryName)) {
                println("Invalid name. Only letters are allowed, no numbers or symbols.")
                return
            }

            val result = categoryManager.add(categoryName)
            println(if (result) "Category '$categoryName' added successfully." else "Category already exists.")
        } else {
            println("Invalid category name.")
        }
    }

    fun update() {
        val categories = categoryManager.getAll()
        categories.forEach { println("[${it.id}] ${it.name}") }
        println("[${categories.size + 1}] Custom Category")
        println()

        print("Enter the category ID to update: ")
        val idToUpdate = readlnOrNull()?.toIntOrNull()

        if (idToUpdate == null) {
            println("Invalid input. Please enter a valid number.")
            return
        }

        val oldCategory = categoryManager.getById(idToUpdate)
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

            if (categoryManager.checkExists(newName)) {
                println("Error: '$newName' already exists.")
            } else {
                val result = categoryManager.update(idToUpdate, newName)
                println(if (result) "Updated successfully." else "Update failed.")
            }
        } else {
            println("Invalid input.")
        }
    }

    fun delete() {
        val categories = categoryManager.getAll()
        categories.forEach { println("[${it.id}] ${it.name}") }

        print("Enter the category ID to delete: ")
        val idToDelete = readlnOrNull()?.toIntOrNull()

        if (idToDelete == null) {
            println("Invalid input. Please enter a valid number.")
            return
        }

        val result = categoryManager.delete(idToDelete)
        println(if (result) "Category deleted." else "Category with ID $idToDelete not found.")
    }

    fun checkName() {
        print("Enter category name to check: ")
        val name = readlnOrNull()?.trim()
        if (!name.isNullOrEmpty()) {
            println(if (categoryManager.checkExists(name)) "Category exists." else "Category does not exist.")
        } else {
            println("Invalid input.")
        }
    }

    fun getAll(): List<Category> {
        return categoryManager.getAll()
    }

    fun select(): Category {
        val categories = categoryManager.getAll()

        if (categories.isEmpty()) {
            println("⚠️ No categories found. Please add a category.")
            add()
            val newCategory = categoryManager.getAll().last()
            println("✅ You added: ${newCategory.name}")
            return newCategory
        }

        println("\n📋 All Categories:")
        categories.forEach { category ->
            println("${category.id} - ${category.name}")
        }
        println("${categories.size + 1} - Custom Category")
        println("🔽 Select a category by entering its number, or Enter Custom Category")
        var selectedId = readlnOrNull()?.toIntOrNull()

        while (selectedId == null || selectedId > categories.size + 1) {
            println("❌ Invalid selection. Please enter a valid category number:")
            selectedId = readlnOrNull()?.toIntOrNull()
        }
        if (selectedId == (categories.size + 1)) add()


        val selectedCategory = categoryManager.getById(selectedId)
        if (selectedCategory == null) {
            println("❌ Invalid selection. Please enter a valid category number:")
        }
        println("✅ You selected: ${selectedCategory?.name}")
        return selectedCategory!!
    }

    private fun isValidCategoryName(name: String): Boolean {
        val regex = Regex("^[\\p{L}\\s]+$")
        return regex.matches(name)
    }

}
