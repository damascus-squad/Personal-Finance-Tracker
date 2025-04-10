package category_feature.feature

import category_feature.model.Category
import java.util.*


class CategoryManagerImpl(initialCategories: List<String>) : CategoryManager {

    private val categories = initialCategories.toCategoryList()

    override fun add(name: String): Boolean {
        val lowerName = name.lowercase(Locale.getDefault())
        return if (checkExists(lowerName)) {
            false
        } else {
            val newId = categories.size + 1
            categories.add(Category(newId, lowerName))
            true
        }
    }

    override fun update(oldName: String, newName: String): Boolean {
        val lowerOld = oldName.lowercase(Locale.getDefault())
        val lowerNew = newName.lowercase(Locale.getDefault())
        val category = categories.find { it.name == lowerOld }

        return if (category != null) {
            val index = categories.indexOf(category)
            categories[index] = category.copy(name = lowerNew)
            true
        } else {
            false
        }
    }

    override fun delete(id: Int): Boolean {
        val category = categories.find { it.id == id }
        return if (category != null) {
            categories.remove(category)
            categories.forEachIndexed { index, cat ->
                categories[index] = cat.copy(id = index + 1)
            }
            true
        } else {
            false
        }
    }

    override fun checkExists(name: String): Boolean {
        return categories.any { it.name == name.lowercase(Locale.getDefault()) }
    }

    override fun getCategories(): String {
        return categories.joinToString("\n") { "[${it.id}] ${it.name}" }
    }

    private fun List<String>.toCategoryList(): MutableList<Category> {
        return this.mapIndexed { index, name ->
            Category(index + 1, name.lowercase(Locale.getDefault()))
        }.toMutableList()
    }
    override fun getCategoryById(id: Int): Category? {
        return categories.find { it.id == id }
    }

}
