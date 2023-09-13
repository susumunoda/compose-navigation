package com.susumunoda.compose.navigation.demoapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.susumunoda.compose.navigation.BottomNavBar
import com.susumunoda.compose.navigation.BottomNavDestination
import com.susumunoda.compose.navigation.demoapp.R

private val HOME = BottomNavDestination(
    route = Destination.HOME.route,
    titleResId = R.string.home_nav_title,
    iconResId = R.drawable.home_icon
)
private val SEARCH = BottomNavDestination(
    route = Destination.SEARCH.route,
    titleResId = R.string.search_nav_title,
    iconResId = R.drawable.search_icon
)
private val FAVORITES = BottomNavDestination(
    route = Destination.FAVORITES.route,
    titleResId = R.string.favorites_nav_title,
    iconResId = R.drawable.star_icon
)
private val SETTINGS = BottomNavDestination(
    route = Destination.SETTINGS.route,
    titleResId = R.string.settings_nav_title,
    iconResId = R.drawable.settings_icon
)
private val BOTTOM_NAV_DESTINATIONS = listOf(HOME, SEARCH, FAVORITES, SETTINGS)

@Composable
fun BottomNavigation(navController: NavHostController) {
    BottomNavBar(
        destinations = BOTTOM_NAV_DESTINATIONS,
        navController = navController,
        context = LocalContext.current
    )
}