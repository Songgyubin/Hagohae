package com.gyub.hagohae.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gyub.hagohae.mission.missionDetailScreen
import com.gyub.hagohae.navigation.Route

/**
 * 홈 Navigation
 *
 * @author   Gyub
 * @created  2024/10/02
 */
fun NavGraphBuilder.homeScreen(
    innerPadding: PaddingValues,
    onBackClick: () -> Unit,
    navigateMissionDetail: (Boolean) -> Unit,
) {
    composable<Route.Home> {
        HomeRoute(
            innerPadding = innerPadding,
            navigateMissionDetail = navigateMissionDetail
        )
    }

    missionDetailScreen(
        innerPadding = innerPadding,
        onBackClick = onBackClick
    )
}