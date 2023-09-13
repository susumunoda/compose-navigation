package com.susumunoda.compose.navigation.demoapp.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.susumunoda.compose.navigation.composableWithoutTransitions
import com.susumunoda.compose.navigation.demoapp.ui.screen.FavoritesScreen
import com.susumunoda.compose.navigation.demoapp.ui.screen.HomeScreen
import com.susumunoda.compose.navigation.demoapp.ui.screen.SearchScreen
import com.susumunoda.compose.navigation.demoapp.ui.screen.homeNavigation
import com.susumunoda.compose.navigation.demoapp.ui.screen.settingsNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootNavigation(navController: NavHostController = rememberNavController()) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination =
        Destination.values().find { it.route == currentBackStackEntry?.destination?.route }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    if (currentDestination?.topBarTitleResId != null) {
                        Text(stringResource(currentDestination.topBarTitleResId))
                    }
                },
                navigationIcon = {
                    if (currentDestination?.showBackButton == true) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Filled.ArrowBack, null)
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigation(navController)
        }
    ) { contentPadding ->
        Box(Modifier.padding(contentPadding)) {
            NavHost(navController = navController, startDestination = Destination.HOME.route) {
                homeNavigation(navController)
                composableWithoutTransitions(Destination.SEARCH.route) {
                    SearchScreen()
                }
                composableWithoutTransitions(Destination.FAVORITES.route) {
                    FavoritesScreen()
                }
                settingsNavigation(navController)
            }
        }
    }
}
