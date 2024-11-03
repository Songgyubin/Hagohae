package com.gyub.hagohae.service

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import java.util.Calendar

/**
 * 앱 차단 Service
 *
 * @author   Gyub
 * @created  2024/09/20
 */
class AppBlockerAccessibilityService : AccessibilityService() {

    private var blockedApps: Set<String> = emptySet()
    private var blockStartTime: Int = -1 // 차단 시작 시간 (24시간제, 예: 2300)
    private var blockEndTime: Int = -1   // 차단 종료 시간 (24시간제, 예: 700)

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
        val filter = IntentFilter(applicationContext.packageName)
        registerReceiver(updateReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val packageName = event.packageName?.toString()
            Log.d("TAG", "현재 앱 패키지: $packageName")
            if (packageName != null && blockedApps.contains(packageName) && isWithinBlockTime()) {
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
        blockStartTime = sharedPreferences.getInt("block_start_time", -1)
        blockEndTime = sharedPreferences.getInt("block_end_time", -1)
    }

    private fun isWithinBlockTime(): Boolean {
        val currentTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) * 100 + Calendar.getInstance().get(Calendar.MINUTE)
        return if (blockStartTime != -1 && blockEndTime != -1) {
            if (blockStartTime < blockEndTime) {
                currentTime in blockStartTime..blockEndTime
            } else {
                currentTime >= blockStartTime || currentTime <= blockEndTime
            }
        } else {
            false
        }
    }

    private val updateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            loadBlockedApps()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(updateReceiver)
    }
}
