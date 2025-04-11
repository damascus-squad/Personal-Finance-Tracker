package category_feature.test

import category_feature.feature.CategoryManagerImpl
class TestCategory()  {
    fun test() {
        val categoryManagerImpl = CategoryManagerImpl(listOf("Food", "Transport", "Entertainment"))
        println("Categories :\n${categoryManagerImpl.getCategories()}")


        check(
            "Should return true when adding Category",
            categoryManagerImpl.add("Salary"),
            true
        )

        check(
            "Should return false when adding duplicate Category",
            categoryManagerImpl.add("Food"),
            false
        )

        check(
            "Should return true when updating Category",
            categoryManagerImpl.update("Transport", "Rent"),
            true
        )

        check(
            "Should return false when updating non-existing category",
            categoryManagerImpl.update("Housing", "Luxury"),
            false
        )

        check(
            "Should return true when deleting Category",
            categoryManagerImpl.delete(1),
            true
        )

        check(
            "Should return false when deleting non-existing category",
            categoryManagerImpl.delete(1),
            false
        )

        check(
            "Should return true when checking if Category exists",
            categoryManagerImpl.checkExists("Rent"),
            true
        )

        check(
            "Should return false when checking if non-existing Category exists",
            categoryManagerImpl.checkExists("Housing"),
            false
        )

    }
}

fun check(name: String, result: Any?, correctResult: Any?) {

    if (result == correctResult) {
        println("Pass - $name")
    } else {
        println("Fail - $name")
    }
}