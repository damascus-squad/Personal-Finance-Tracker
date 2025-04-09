package category_feature.feature

import category_feature.model.Category
import category_feature.model.CategoryList
import java.util.*


class CategoryManager(initialCategories: List<String>) : CategoryOperations {

    private val categoryList = CategoryList(
        initialCategories.mapIndexed { index, name ->
            Category(index + 1, name.lowercase(Locale.getDefault()))
        }.toMutableList()
    )

    override fun addCategory(name: String): Boolean {
        val lowerName = name.lowercase(Locale.getDefault())
        return if (checkExists(lowerName)) {
            false
        } else {
            val newId = categoryList.categories.size + 1
            categoryList.categories.add(Category(newId, lowerName))
            true
        }
    }

    override fun updateCategory(oldName: String, newName: String): Boolean {
        val lowerOld = oldName.lowercase(Locale.getDefault())
        val lowerNew = newName.lowercase(Locale.getDefault())
        val category = categoryList.categories.find { it.name == lowerOld }

        return if (category != null) {
            val index = categoryList.categories.indexOf(category)
            categoryList.categories[index] = category.copy(name = lowerNew)
            true
        } else {
            false
        }
    }

    override fun deleteCategory(name: String): Boolean {
        val lowerName = name.lowercase(Locale.getDefault())
        val category = categoryList.categories.find { it.name == lowerName }
        return if (category != null) {
            categoryList.categories.remove(category)
            categoryList.categories.forEachIndexed { index, cat ->
                categoryList.categories[index] = cat.copy(id = index + 1)
            }
            true
        } else {
            false
        }
    }

    override fun checkExists(name: String): Boolean {
        return categoryList.categories.any { it.name == name.lowercase(Locale.getDefault()) }
    }

    override fun displayCategories(): String {
        return categoryList.categories.joinToString("\n") { "[${it.id}] ${it.name}" }
    }
}
