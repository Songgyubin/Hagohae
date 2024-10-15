package com.gyub.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 각 미션 Entity
 *
 * @author   Gyub
 * @created  2024/10/15
 */
@Entity(tableName = "mission")
data class MissionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String = "",
    val contents: String = "",
    val blockingStartTime: Long = 0L,
    val blockedApps: List<AppEntity> = emptyList(),
    val tags: List<String> = emptyList(),
) {
    data class AppEntity(
        val appId: String = "",
        val packageName: String = "",
    )
}
