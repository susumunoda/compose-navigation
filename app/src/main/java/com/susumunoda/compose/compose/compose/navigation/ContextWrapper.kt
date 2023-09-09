package com.susumunoda.compose.compose.compose.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

internal class ContextWrapper(private val context: Context) {
    @Composable
    fun <T : Any> run(content: @Composable () -> T): T {
        lateinit var value: T
        CompositionLocalProvider(LocalContext provides context) {
            value = content()
        }
        return value
    }
}