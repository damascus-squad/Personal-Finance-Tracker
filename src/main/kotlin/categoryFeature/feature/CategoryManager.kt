package categoryFeature.feature

import categoryFeature.model.Category

interface CategoryManager {
    fun add(name: String): Boolean
    fun update(id: Int, newName: String): Boolean
    fun delete(id: Int): Boolean
    fun checkExists(name: String): Boolean
    fun getCategories(): List<Category>
    fun getCategoryById(id: Int): Category?
}