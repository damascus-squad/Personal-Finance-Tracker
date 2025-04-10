package category_feature.model

enum class Operation(val description: String) {
    ADD_CATEGORY("Add Category"),
    UPDATE_CATEGORY("Update Category"),
    DELETE_CATEGORY("Delete Category"),
    CHECK_EXISTS("Check if Category exists"),
    DISPLAY_CATEGORIES("Display Categories"),
    EXIT("Back to Main Menu")
}
