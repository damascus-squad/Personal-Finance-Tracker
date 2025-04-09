package category_feature.model

enum class Operation(val description: String) {
    SELECT_CATEGORY("Select a Category"),
    ADD_CATEGORY("Add Category"),
    UPDATE_CATEGORY("Update Category"),
    DELETE_CATEGORY("Delete Category"),
    CHECK_EXISTS("Check if Category exists"),
    DISPLAY_CATEGORIES("Display Categories"),
    EXIT("Exit")
}
