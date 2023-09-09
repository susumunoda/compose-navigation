package com.susumunoda.compose.navigation

import android.content.Context
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigation(
    destinations: List<Destination>,
    navController: NavHostController,
    context: Context,
    modifier: Modifier = Modifier,
    startDestination: Destination = destinations[0],
    iconSize: Dp = dimensionResource(R.dimen.icon),
    fontSize: TextUnit = TextUnit.Unspecified,
    windowInsets: WindowInsets = WindowInsets(
        left = 0.dp,
        top = 0.dp,
        right = 0.dp,
        bottom = dimensionResource(R.dimen.padding_small)
    )
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val backStackHierarchy = currentBackStackEntry?.destination?.hierarchy
    val contextWrapper = remember(context) { ContextWrapper(context) }

    NavigationBar(
        modifier = modifier,
        windowInsets = windowInsets,
    ) {
        destinations.forEach() { destination ->
            val selected = backStackHierarchy?.any { it.route == destination.route } == true
            val title = contextWrapper.run { stringResource(destination.titleResId) }
            val iconPainter = contextWrapper.run { painterResource(destination.iconResId) }

            NavigationBarItem(
                label = { Text(title, fontSize = fontSize) },
                icon = { Icon(iconPainter, title, Modifier.size(iconSize)) },
                selected = selected,
                onClick = {
                    // Navigate to the root of the currently selected top-level destination
                    if (selected) {
                        // Find the root destination or NavGraph
                        val popToDestination = backStackHierarchy?.last { dest ->
                            !(dest is NavGraph && dest.startDestinationRoute == startDestination.route)
                        }
                        // If the root is simply a destination and not a nested NavGraph, just pop
                        // to there; otherwise, pop to the start destination of the nested graph.
                        when (popToDestination) {
                            is ComposeNavigator.Destination -> {
                                navController.popBackStack(popToDestination.route!!, false)
                            }

                            is NavGraph -> {
                                navController.popBackStack(
                                    popToDestination.startDestinationRoute!!,
                                    false
                                )
                            }
                        }
                    } else {
                        // If navigating to a different top-level destination, save the state
                        // (including backstack) of the current destination and restore it for the
                        // destination being navigated to.
                        navController.navigate(destination.route) {
                            // Pop up to the main navigation root to avoid creating a large backstack
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}
