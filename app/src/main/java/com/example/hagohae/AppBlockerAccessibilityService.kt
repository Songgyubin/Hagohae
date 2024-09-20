package com.example.hagohae

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import androidx.compose.runtime.mutableStateListOf

/**
 *
 *
 * @author   Gyub
 * @created  2024/09/20
 */
class AppBlockerAccessibilityService : AccessibilityService() {

    private var blockedApps: Set<String> = emptySet()

    override fun onServiceConnected() {
        super.onServiceConnected()
        loadBlockedApps()
        Log.d("TAG", "Accessibility Service Connected: ${applicationContext.packageName}")
        val info = AccessibilityServiceInfo().apply {
            eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
            packageNames = null // 모든 패키지에 대해 이벤트 수신
            feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
            notificationTimeout = 100
            flags = AccessibilityServiceInfo.DEFAULT
        }
        this.serviceInfo = info
        // 변경 사항을 수신하기 위한 브로드캐스트 리시버 등록
        val filter = IntentFilter(applicationContext.packageName)
        registerReceiver(updateReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.d("TAG", "onAccessibilityEvent$packageName")
        if (event?.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val packageName = event.packageName?.toString()
            Log.d("TAG", "현재 앱 패키지: $packageName")
            if (packageName != null && blockedApps.contains(packageName)) {
                Log.d("TAG", "차단된 앱 실행 감지: $packageName")
                blockApp()
            }
        }
    }

    override fun onInterrupt() {}

    private fun blockApp() {
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_HOME)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }

    private fun loadBlockedApps() {
        val sharedPreferences = getSharedPreferences("blocked_apps_prefs", Context.MODE_PRIVATE)
        blockedApps = sharedPreferences.getStringSet("blocked_apps", emptySet()) ?: emptySet()
    }

    private val updateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("TAG", " Received broadcast to update blocked apps")
            loadBlockedApps()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(updateReceiver)
    }
}