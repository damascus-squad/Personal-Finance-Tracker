package org.example.storage

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object LocalDateTimeSerializer : KSerializer<LocalDateTime> {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        val str = value.format(formatter)
        encoder.encodeString(str)
    }

    override fun deserialize(decoder: Decoder): LocalDateTime {
        val str = decoder.decodeString()
        return try {
            LocalDateTime.parse(str, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        } catch (e: Exception) {
            LocalDateTime.of(
                LocalDate.parse(str, DateTimeFormatter.ISO_LOCAL_DATE),
                LocalTime.MIDNIGHT
            )
        }
    }

}