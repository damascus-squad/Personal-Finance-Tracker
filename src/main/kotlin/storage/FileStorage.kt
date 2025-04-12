package org.example.storage

interface FileStorage<T> {
    fun save(newData: List<T>)
    fun load(): List<T>
}