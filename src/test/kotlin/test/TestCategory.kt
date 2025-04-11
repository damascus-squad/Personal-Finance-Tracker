package test

import category_feature.feature.CategoryManagerImpl
import util.CheckTest
import util.check


class TestCategory {

    private val categoryManagerImpl = CategoryManagerImpl(listOf("Food", "Transport", "Entertainment"))

    @CheckTest
    fun testAddCategory(): Boolean {
        return check(
            "Should return true when adding Category",
            categoryManagerImpl.add("Salary"),
            true
        )
    }

    @CheckTest
    fun testAddDuplicateCategory(): Boolean {
        return check(
            "Should return false when adding duplicate Category",
            categoryManagerImpl.add("Food"),
            false
        )
    }

    @CheckTest
    fun testUpdateCategory(): Boolean {
        return check(
            "Should return true when updating Category",
            categoryManagerImpl.update(2, "Rent"),
            true
        )
    }

    @CheckTest
    fun testUpdateNonExistingCategory(): Boolean {
        return check(
            "Should return false when updating non-existing category",
            categoryManagerImpl.update(5, "Luxury"),
            false
        )
    }

    @CheckTest
    fun testDeleteCategory(): Boolean {
        return check(
            "Should return true when deleting Category",
            categoryManagerImpl.delete(4),
            true
        )
    }

    @CheckTest
    fun testDeleteNonExistingCategory(): Boolean {
        return check(
            "Should return false when deleting non-existing category",
            categoryManagerImpl.delete(100),
            false
        )
    }

    @CheckTest
    fun testCheckExistsCategory(): Boolean {
        return check(
            "Should return true when checking if Category exists",
            categoryManagerImpl.checkExists("Food"),
            true
        )
    }

    @CheckTest
    fun testCheckNonExistingCategory(): Boolean {
        return check(
            "Should return false when checking if non-existing Category exists",
            categoryManagerImpl.checkExists("Housing"),
            false
        )
    }
}
