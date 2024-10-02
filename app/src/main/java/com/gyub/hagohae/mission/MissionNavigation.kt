package com.gyub.hagohae.mission

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gyub.hagohae.navigation.Route

/**
 * 미션 상세 navigation
 *
 * @author   Gyub
 * @created  2024/10/02
 */
fun NavController.navigateToDetail(
    isEdit: Boolean,
) = navigate(
    Route.MissionDetail(
        isEdit = isEdit
    )
)

fun NavGraphBuilder.missionDetailScreen(
    innerPadding: PaddingValues,
    onBackClick: () -> Unit,
) {
    composable<Route.MissionDetail> {
        MissionDetailRoute(
            innerPadding = innerPadding,
            onBackClick = onBackClick
        )
    }
}