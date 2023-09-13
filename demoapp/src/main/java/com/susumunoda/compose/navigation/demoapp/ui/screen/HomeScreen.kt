package com.susumunoda.compose.navigation.demoapp.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.susumunoda.compose.animation.enterSlidingUp
import com.susumunoda.compose.animation.exitSlidingDown
import com.susumunoda.compose.navigation.composableWithConditionalTransitions
import com.susumunoda.compose.navigation.composableWithoutTransitions
import com.susumunoda.compose.navigation.demoapp.ui.navigation.Destination

fun NavGraphBuilder.homeNavigation(navController: NavHostController) {
    navigation(
        route = Destination.HOME.route,
        startDestination = Destination.HOME_ROOT.route
    ) {
        composableWithoutTransitions(Destination.HOME_ROOT.route) {
            HomeScreen(navController)
        }
        composableWithConditionalTransitions(
            route = Destination.POPUP.route,
            enterTransition = { enterSlidingUp() },
            enterTransitionFrom = Destination.HOME_ROOT.route,
            exitTransition = { exitSlidingDown() },
            exitTransitionTo = Destination.HOME_ROOT.route
        ) {
            PopupScreen()
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("This is the home screen")
            Button(onClick = { navController.navigate(Destination.POPUP.route) }) {
                Text("Open popup")
            }
        }
    }
}

@Composable
fun PopupScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("This is the popup screen")
    }
}