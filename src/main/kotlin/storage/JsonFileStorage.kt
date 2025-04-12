package org.example.storage

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.io.File

class JsonFileStorage<T>(
    private val filePath: String,
    private val serializer: KSerializer<List<T>>
) : FileStorage<T> {

    override fun save(newData: List<T>, overwrite: Boolean) {
        val data = newData.toMutableList()
        if (!overwrite) {
            val existingData = load()
            data += existingData
        }
        val json = Json.encodeToString(serializer, data)
        File(filePath).writeText(json)
    }

    override fun load(): List<T> = runCatching {
        val file = File(filePath)
        if (!file.exists()) return emptyList()
        val content = file.readText()
        return Json.decodeFromString(serializer, content)
    }.getOrDefault(emptyList())

}
