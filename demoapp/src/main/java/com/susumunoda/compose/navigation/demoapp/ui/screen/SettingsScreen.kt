package com.susumunoda.compose.navigation.demoapp.ui.screen

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.susumunoda.compose.animation.enterSlidingLeft
import com.susumunoda.compose.animation.exitSlidingRight
import com.susumunoda.compose.navigation.composableWithConditionalTransitions
import com.susumunoda.compose.navigation.composableWithoutTransitions
import com.susumunoda.compose.navigation.demoapp.R
import com.susumunoda.compose.navigation.demoapp.ui.navigation.Destination

private enum class SettingConfig(
    @StringRes val menuOptionTitleResId: Int,
    val navDestination: Destination
) {
    SETTING_ONE(R.string.setting_one_menu_option_title, Destination.SETTING_ONE),
    SETTING_TWO(R.string.setting_two_menu_option_title, Destination.SETTING_TWO),
    SETTING_THREE(R.string.setting_three_menu_option_title, Destination.SETTING_THREE),
}

fun NavGraphBuilder.settingsNavigation(navController: NavHostController) {
    navigation(
        route = Destination.SETTINGS.route,
        startDestination = Destination.SETTINGS_ROOT.route
    ) {
        composableWithoutTransitions(Destination.SETTINGS_ROOT.route) {
            SettingsRootScreen(navController, SettingConfig.values())
        }
        settingsScreenComposable(Destination.SETTING_ONE.route) {
            SettingOneScreen()
        }
        settingsScreenComposable(Destination.SETTING_TWO.route) {
            SettingTwoScreen()
        }
        settingsScreenComposable(Destination.SETTING_THREE.route) {
            SettingThreeScreen()
        }
    }
}

private fun NavGraphBuilder.settingsScreenComposable(
    route: String,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composableWithConditionalTransitions(
        route = route,
        enterTransition = { enterSlidingLeft() },
        enterTransitionFrom = Destination.SETTINGS_ROOT.route,
        exitTransition = { exitSlidingRight() },
        exitTransitionTo = Destination.SETTINGS_ROOT.route,
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsRootScreen(
    navController: NavHostController,
    settingConfigs: Array<SettingConfig>,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.fillMaxSize()) {
        LazyColumn {
            items(settingConfigs) { settingConfig ->
                ListItem(
                    headlineText = { Text(stringResource(settingConfig.menuOptionTitleResId)) },
                    trailingContent = {
                        Icon(
                            Icons.Filled.KeyboardArrowRight,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier.clickable {
                        navController.navigate(settingConfig.navDestination.route)
                    }
                )
                Divider()
            }
        }
    }
}

@Composable
private fun SettingOneScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center) {
            Text("This is the setting 1 screen")
        }
    }
}

@Composable
private fun SettingTwoScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center) {
            Text("This is the setting 2 screen")
        }
    }
}

@Composable
private fun SettingThreeScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center) {
            Text("This is the setting 3 screen")
        }
    }
}