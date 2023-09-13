package com.susumunoda.compose.navigation.demoapp.ui.navigation

import androidx.annotation.StringRes
import com.susumunoda.compose.navigation.demoapp.R

enum class Destination(
    val route: String,
    @StringRes val topBarTitleResId: Int? = null,
    val showBackButton: Boolean = false
) {
    HOME("home"),
    HOME_ROOT("home_root", R.string.home_top_bar_title),
    SEARCH("search", R.string.search_top_bar_title),
    FAVORITES("favorites", R.string.favorites_top_bar_title),
    SETTINGS("settings"),
    SETTINGS_ROOT("settings_root", R.string.settings_top_bar_title),
    SETTING_ONE("setting_1", R.string.setting_one_top_bar_title, true),
    SETTING_TWO("setting_2", R.string.setting_two_top_bar_title, true),
    SETTING_THREE("setting_3", R.string.setting_three_top_bar_title, true),
    POPUP("popup", R.string.popup_top_bar_title, true)
}
