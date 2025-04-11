package org.example.storage

interface FileStorage<T> {
    fun saveAndAppend(newData: List<T>)
    fun load(): List<T>
}