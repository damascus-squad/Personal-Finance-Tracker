package org.example.storage

import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.serializer

object FileStorageFactory {
    inline fun <reified T : Any> create(filePath: String): FileStorage<T> {
        val serializer = ListSerializer(serializer<T>())
        return JsonFileStorage(filePath, serializer)
    }
}
