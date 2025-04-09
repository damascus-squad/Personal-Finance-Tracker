package category_feature.feature

import category_feature.model.CategoryList

class CategoryManager(initialCategories: List<String>): CategoryOperations {

    private val categoryList = CategoryList(initialCategories.toMutableList())

    override fun addCategory(name: String): Boolean {
        return false
    }

    override fun updateCategory(oldName: String, newName: String): Boolean {
        return false
    }

    override fun deleteCategory(name: String): Boolean {
        return false
    }

    override fun checkExists(name: String): Boolean {
        return false
    }

    override fun displayCategories(): String {
        val stringBuilder = StringBuilder()
        categoryList.categories.forEachIndexed { index, category ->
            stringBuilder.append("[${index + 1}] $category\n")
        }
        return stringBuilder.toString()
    }
}