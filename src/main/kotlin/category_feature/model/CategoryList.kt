package category_feature.model

data class Category(
    val id: Int,
    val name: String
)

data class CategoryList(
    val categories: MutableList<Category>
)