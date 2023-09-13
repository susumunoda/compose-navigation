package com.susumunoda.compose.navigation.demoapp.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.susumunoda.compose.navigation.BottomNavigation
import com.susumunoda.compose.navigation.Destination
import com.susumunoda.compose.navigation.demoapp.R
import com.susumunoda.compose.navigation.demoapp.ui.screen.FavoritesScreen
import com.susumunoda.compose.navigation.demoapp.ui.screen.HomeScreen
import com.susumunoda.compose.navigation.demoapp.ui.screen.SearchScreen
import com.susumunoda.compose.navigation.demoapp.ui.screen.SettingsScreen

private val HOME = Destination("Home", R.string.nav_home, R.drawable.home_icon)
private val SEARCH = Destination("Search", R.string.nav_search, R.drawable.search_icon)
private val FAVORITES = Destination("Favorites", R.string.nav_favorites, R.drawable.star_icon)
private val SETTINGS = Destination("Settings", R.string.nav_settings, R.drawable.settings_icon)
private val DESTINATIONS = listOf(HOME, SEARCH, FAVORITES, SETTINGS)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootNavigation(navController: NavHostController = rememberNavController()) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination =
        DESTINATIONS.find { it.route == currentBackStackEntry?.destination?.route }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    if (currentDestination != null) {
                        Text(stringResource(currentDestination.titleResId))
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigation(
                destinations = DESTINATIONS,
                navController = navController,
                context = LocalContext.current
            )
        }
    ) { contentPadding ->
        Box(Modifier.padding(contentPadding)) {
            NavHost(navController = navController, startDestination = HOME.route) {
                composable(
                    route = HOME.route,
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None }
                ) {
                    HomeScreen()
                }
                composable(
                    route = SEARCH.route,
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None }
                ) {
                    SearchScreen()
                }
                composable(
                    route = FAVORITES.route,
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None }
                ) {
                    FavoritesScreen()
                }
                composable(
                    route = SETTINGS.route,
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None }
                ) {
                    SettingsScreen()
                }
            }
        }
    }
}
