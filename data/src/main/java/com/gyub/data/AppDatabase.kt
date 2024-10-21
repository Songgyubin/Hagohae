package com.gyub.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gyub.data.dao.MissionDao
import com.gyub.data.model.MissionEntity

/**
 * Hagohae Database
 *
 * @author   Gyub
 * @created  2024/10/15
 */
@Database(
    entities = [
        MissionEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
   abstract fun missionDao(): MissionDao
}
