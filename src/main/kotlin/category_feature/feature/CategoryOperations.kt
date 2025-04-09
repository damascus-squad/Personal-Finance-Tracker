package category_feature.feature

interface CategoryOperations {

    fun addCategory(name: String): Boolean

    fun updateCategory(oldName: String, newName: String): Boolean

    fun deleteCategory(name: String): Boolean

    fun checkExists(name: String): Boolean

    fun displayCategories(): String
}