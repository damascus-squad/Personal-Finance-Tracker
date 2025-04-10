package category_feature.test

import category_feature.feature.CategoryManagerImpl
class TestCategory() {
    fun test() {
        val categoryManagerImpl = CategoryManagerImpl(listOf("Food", "Transport", "Entertainment"))
        println("Categories :\n${categoryManagerImpl.getCategories()}")

//region add Category
        // Test adding a new category
        checkCategory(
            "Should return true when adding Category",
            categoryManagerImpl.add("Salary"),
            true
        )

        // Test adding an existing category (ensure that the category is not added again)
        checkCategory(
            "Should return false when adding duplicate Category",
            categoryManagerImpl.add("Food"),
            false
        )
//endregion

//region update Category
        // Test updating an existing category
        checkCategory(
            "Should return true when updating Category",
            categoryManagerImpl.update("Transport", "Rent"),
            true
        )

        // Test trying to update a non-existing category
        checkCategory(
            "Should return false when updating non-existing category",
            categoryManagerImpl.update("Housing", "Luxury"),
            false
        )
//endregion

//region delete Category
        // Test deleting an existing category
        checkCategory(
            "Should return true when deleting Category",
            categoryManagerImpl.delete(1),
            true
        )

        // Test trying to delete a non-existing category
        checkCategory(
            "Should return false when deleting non-existing category",
            categoryManagerImpl.delete(1),
            false
        )
//endregion

//region check Exists Category
        // Test checking if an existing category exists
        checkCategory(
            "Should return true when checking if Category exists",
            categoryManagerImpl.checkExists("Rent"),
            true
        )

        // Test checking if a non-existing category exists
        checkCategory(
            "Should return false when checking if non-existing Category exists",
            categoryManagerImpl.checkExists("Housing"),
            false
        )
//endregion

    }
}

fun checkCategory(name: String, result: Any?, correctResult: Any?) {

    if (result == correctResult) {
        println("Pass - $name")
    } else {
        println("Fail - $name")
    }
}