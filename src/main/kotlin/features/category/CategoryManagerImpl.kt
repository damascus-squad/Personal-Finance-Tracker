package features.category

import org.example.features.category.CategoryManager
import org.example.model.Category
import org.example.storage.FileStorage
import java.util.*


class CategoryManagerImpl(initialCategories: List<String>, private val storage: FileStorage<Category>) :
    CategoryManager {

    private var categories = initialCategories.toCategoryList()

    override fun add(name: String): Boolean {
        val lowerName = name.lowercase(Locale.getDefault())
        return if (checkExists(lowerName)) {
            false
        } else {
            val newId = categories.size + 1
            categories.add(Category(newId, lowerName))
            storage.save(categories, overwrite = true)
            true
        }
    }

    override fun update(id: Int, newName: String): Boolean {
        val category = categories.find { it.id == id }

        return if (category != null) {
            val index = categories.indexOf(category)
            categories[index] = category.copy(name = newName)
            storage.save(categories, overwrite = true)
            true
        } else {
            false
        }
    }

    override fun delete(id: Int): Boolean {
        val index = id - 1
        return if (index in categories.indices) {
            categories.removeAt(index)
            storage.save(categories, overwrite = true)
            true
        } else {
            false
        }
    }

    override fun checkExists(name: String): Boolean {
        return categories.any { it.name == name.lowercase(Locale.getDefault()) }
    }

    override fun getAll(): List<Category> {
        categories = storage.load().toMutableList()
        return categories.mapIndexed { index, cat ->
            cat.copy(id = index + 1)
        }
    }

    private fun List<String>.toCategoryList(): MutableList<Category> {
        return this.mapIndexed { index, name ->
            Category(index + 1, name.lowercase(Locale.getDefault()))
        }.toMutableList()
    }
    override fun getById(id: Int): Category? {
        return categories.find { it.id == id }
    }


}
