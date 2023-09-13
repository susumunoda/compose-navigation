package com.susumunoda.compose.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class BottomNavDestination(
    val route: String,
    @StringRes val titleResId: Int,
    @DrawableRes val iconResId: Int,
)