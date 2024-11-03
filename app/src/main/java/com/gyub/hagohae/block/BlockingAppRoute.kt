package com.gyub.hagohae.block

import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

/**
 * 차단할 앱 화면
 *
 * @author   Gyub
 * @created  2024/11/03
 */
@Composable
fun BlockingAppRoute() {
    BlockedApps()
}

@Composable
fun BlockedApps() {
    val context = LocalContext.current
    val packageManager = context.packageManager
    val installedApps = remember { packageManager.getInstalledApplications(PackageManager.GET_META_DATA) }
    val sharedPreferences = context.getSharedPreferences("blocked_apps_prefs", Context.MODE_PRIVATE)
    val blockedApps = remember { mutableStateOf(sharedPreferences.getStringSet("blocked_apps", emptySet()) ?: emptySet()) }
    LazyColumn {
        items(installedApps) { app ->
            val appName = app.loadLabel(packageManager).toString()
            val appPackage = app.packageName
            val isBlocked = blockedApps.value.contains(appPackage)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(appName)
                Checkbox(
                    checked = isBlocked,
                    onCheckedChange = { isChecked ->
                        val updatedBlockedApps = blockedApps.value.toMutableSet()
                        if (isChecked) {
                            updatedBlockedApps.add(appPackage)
                        } else {
                            updatedBlockedApps.remove(appPackage)
                        }
                        blockedApps.value = updatedBlockedApps
                        sharedPreferences.edit().putStringSet("blocked_apps", updatedBlockedApps).apply()
                    }
                )
            }
        }
    }
}
