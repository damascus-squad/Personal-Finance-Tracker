package org.example.storage

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.io.File

class JsonFileStorage<T>(
    private val filePath: String,
    private val serializer: KSerializer<List<T>>
) : FileStorage<T> {

    override fun save(newData: List<T>) {
        val existingData = load()
        val combinedData = existingData + newData
        val json = Json.encodeToString(serializer, combinedData)
        File(filePath).writeText(json)
    }

    //FIXME handle exception file empty
    override fun load(): List<T> {
        val file = File(filePath)
        if (!file.exists()) return emptyList()
        val content = file.readText()
        return Json.decodeFromString(serializer, content)
    }

    override fun overWrite(allData: List<T>) {
        val json = Json.encodeToString(serializer, allData)
        File(filePath).writeText(json)
    }

}
