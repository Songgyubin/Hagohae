package com.gyub.data

import androidx.room.TypeConverter
import com.gyub.data.model.MissionEntity
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

/**
 * 참조 타입의 프로퍼티를 저장하기 위한 Room Converter
 *
 * @author   Gyub
 * @created  2024/10/21
 */
class Converters {

    // List<String> -> String (JSON 형태로 변환)
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return Json.encodeToString(ListSerializer(String.serializer()), value)
    }

    // String (JSON) -> List<String> (원래 List<String>으로 복원)
    @TypeConverter
    fun toStringList(value: String): List<String> {
        return Json.decodeFromString(ListSerializer(String.serializer()), value)
    }

    // List<AppEntity> -> String (JSON 형태로 변환)
    @TypeConverter
    fun fromAppEntityList(value: List<MissionEntity.AppEntity>): String {
        return Json.encodeToString(ListSerializer(MissionEntity.AppEntity.serializer()), value)
    }

    // String (JSON) -> List<AppEntity> (원래 List<AppEntity>으로 복원)
    @TypeConverter
    fun toAppEntityList(value: String): List<MissionEntity.AppEntity> {
        return Json.decodeFromString(ListSerializer(MissionEntity.AppEntity.serializer()), value)
    }
}
