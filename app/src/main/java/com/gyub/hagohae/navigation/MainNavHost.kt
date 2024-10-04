package com.gyub.hagohae.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.gyub.hagohae.home.navigation.homeScreen
import com.gyub.hagohae.mission.navigation.missionDetailScreen

/**
 * Main Nav Host
 *
 * @author   Gyub
 * @created  2024/10/04
 */
@Composable
fun MainNavHost(
    navController: NavHostController = rememberNavController(),
    innerPadding: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = Route.Home
    ) {
        homeScreen(
            innerPadding = innerPadding,
            onBackClick = {},
            navigateMissionDetail = { },
        )
        missionDetailScreen(
            innerPadding = innerPadding,
            onBackClick = { },
        )
    }
}