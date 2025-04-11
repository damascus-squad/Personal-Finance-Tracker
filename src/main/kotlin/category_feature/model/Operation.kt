package category_feature.model

enum class Operation(val description: String) {
    ADD_CATEGORY("Add Category"),
    UPDATE_CATEGORY("Update Category"),
    DELETE_CATEGORY("Delete Category"),
    CHECK_EXISTS("Check If Category Exists"),
    DISPLAY_CATEGORIES("Display Categories"),
    BACK("Back To Main Menu")
}
