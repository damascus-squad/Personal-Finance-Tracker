package org.example.storage

interface FileStorage<T> {
    fun save(newData: List<T>, overwrite: Boolean = false)
    fun load(): List<T>
}