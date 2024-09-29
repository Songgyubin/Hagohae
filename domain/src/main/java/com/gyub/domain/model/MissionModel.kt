package com.gyub.domain.model

/**
 * 미션 도메인 모델
 *
 * @author   Gyub
 * @created  2024/09/29
 */
data class MissionModel(
    val title: String = "",
    val contents: String = "",
    val blockingStartTime: Long = 0L,
    val blockedApps: List<AppModel> = emptyList(),
    val tags: List<String> = emptyList(),
) {
    data class AppModel(
        val packageName: String = "",
    )
}