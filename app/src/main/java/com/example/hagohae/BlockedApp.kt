package com.gyub.hagohae

/**
 *
 *
 * @author   Gyub
 * @created  2024/09/20
 */
data class BlockedApp(
    val packageName: String,
    val unblockTime: Long // 차단 해제 시간 (밀리초)
)