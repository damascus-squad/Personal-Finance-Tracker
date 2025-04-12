package org.example.model

enum class Operation(val description: String) {
    AddCategory("Add Category"),
    UpdateCategory("Update Category"),
    DeleteCategory("Delete Category"),
    CheckExists("Check If Category Exists"),
    DisplayCategories("Display Categories"),
    Back("Back To Main Menu")
}
