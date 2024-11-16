package com.gyub.hagohae.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable

@SuppressLint("ComposableNaming")
@Composable
inline fun String.ifNotBlank(content: @Composable (String) -> Unit) {
    if (this.isNotBlank()) content(this)
}
