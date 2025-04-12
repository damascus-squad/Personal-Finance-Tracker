package org.example.features.category

import org.example.model.Category

interface CategoryManager {
    fun add(name: String): Boolean
    fun update(id: Int, newName: String): Boolean
    fun delete(id: Int): Boolean
    fun checkExists(name: String): Boolean
    fun getAll(): List<Category>
    fun getById(id: Int): Category?
}