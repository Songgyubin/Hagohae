package com.gyub.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gyub.domain.model.MissionModel
import kotlinx.serialization.Serializable

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
    @Serializable
    data class AppEntity(
        val appId: String = "",
        val packageName: String = "",
    )
}

fun MissionEntity.toDomainModel() = MissionModel(
    id = id,
    title = title,
    contents = contents,
    blockingStartTime = blockingStartTime,
    blockedApps = blockedApps.map { it.toDomainModel() },
    tags = tags,
)

fun MissionEntity.AppEntity.toDomainModel() = MissionModel.AppModel(
    appId = appId,
    packageName = packageName,
)
