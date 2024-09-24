package com.example.hagohae.navigation

import kotlinx.serialization.Serializable

/**
 * 화면이동
 *
 * @author   Gyub
 * @created  2024/09/21
 */
sealed interface Route {
    @Serializable
    data object Home : Route
    @Serializable
    data class MissionDetail(
        val isEdit: Boolean,
    ) : Route
}
