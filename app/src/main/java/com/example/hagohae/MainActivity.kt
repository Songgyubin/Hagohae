package com.example.hagohae

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.hagohae.ui.theme.HagohaeTheme
import java.util.stream.Collectors.toSet

class MainActivity : ComponentActivity() {

    private val REQUEST_ACCESSIBILITY_SETTINGS = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            checkAccessibilityPermission(LocalContext.current as ComponentActivity)
            HagohaeTheme {
                AppBlockerScreen()

//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Column(
//                        modifier = Modifier.padding(innerPadding)
//                    ) {
//                        Button(
//                            onClick = {
//
//                            },
//                        ) {
//                            Text("차단할 앱")
//                        }
//                        Text(
//                            text = "차단된 앱: ",
//                            modifier = Modifier.padding(16.dp)
//                        )
//                        Button(
//                            onClick = { /*TODO*/ },
//                        ) {
//                            Text("차단 해제")
//                        }
//                    }
//                }
            }
        }
    }

    private fun checkAccessibilityPermission(activity: ComponentActivity) {
        if (!isAccessibilityServiceEnabled()) {
            // 접근성 서비스 설정 화면으로 이동
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            ActivityCompat.startActivityForResult(
                activity,
                intent,
                REQUEST_ACCESSIBILITY_SETTINGS,
                null
            )
        }
    }

    private fun isAccessibilityServiceEnabled(): Boolean {
        val service = "${packageName}/${AppBlockerAccessibilityService::class.java.canonicalName}"
        val enabledServices =
            Settings.Secure.getString(contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)
        return enabledServices?.contains(service) == true
    }
}

@Composable
fun AppBlockerScreen() {
    val context = LocalContext.current
    val pm = context.packageManager
    val apps = remember { mutableStateListOf<ApplicationInfo>() }
    val blockedApps = remember { mutableStateListOf<String>() }

    // 설치된 앱 목록 가져오기
    LaunchedEffect(Unit) {
        val installedApps = pm.getInstalledApplications(PackageManager.GET_META_DATA)
            .filter { (it.flags and ApplicationInfo.FLAG_SYSTEM) == 0 } // 시스템 앱 제외
        apps.addAll(installedApps)
    }

    Scaffold(
        topBar = {
//            TopAppBar(title = { Text("앱 차단기") })
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(apps.size) { index ->
                val app = apps[index]
                Log.d("TAG", " - app: $app")
                val isBlocked = blockedApps.contains(app.packageName)
                AppItem(appInfo = app, isBlocked = isBlocked) { selectedApp ->
                    if (isBlocked) {
                        blockedApps.remove(selectedApp.packageName)
                    } else {
                        blockedApps.add(selectedApp.packageName)
                    }
                    // 차단 앱 목록 업데이트
                    updateBlockedApps(context, blockedApps.toSet())
                    // 접근성 서비스에 변경 사항 알리기
                    val intent = Intent(context.packageName)
                    intent.setPackage(context.packageName) // 또는 "com.example.hagohae"로 대체
                    context.sendBroadcast(intent)
                    Log.d("TAG", "sendBra - : ${context.packageName}")
                }
            }
        }
    }
}

@Composable
fun AppItem(appInfo: ApplicationInfo, isBlocked: Boolean, onAppSelected: (ApplicationInfo) -> Unit) {
    val current = LocalContext.current
    val pm = current.packageManager
    val appName = remember(appInfo) {
        pm.getApplicationLabel(appInfo).toString()
    }
    val appIcon = remember(appInfo) {
        try {
            pm.getApplicationIcon(appInfo)
        } catch (e: PackageManager.NameNotFoundException) {
            ContextCompat.getDrawable(current, R.mipmap.ic_launcher)!!
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onAppSelected(appInfo) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            bitmap = appIcon.toBitmap().asImageBitmap(),
            contentDescription = appName,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = appName, modifier = Modifier.weight(1f))
        Checkbox(
            checked = isBlocked,
            onCheckedChange = { onAppSelected(appInfo) }
        )
    }
}

fun updateBlockedApps(context: Context, blockedApps: Set<String>) {
    val sharedPreferences = context.getSharedPreferences("blocked_apps_prefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putStringSet("blocked_apps", blockedApps)
    editor.apply()
}