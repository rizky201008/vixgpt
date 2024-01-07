package com.vixiloc.vixgpt.presentation.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vixiloc.vixgpt.presentation.home.HomeScreen
import com.vixiloc.vixgpt.presentation.settings.SettingsScreen

@Composable
fun MainNavigationHost(navhostController: NavHostController) {
    NavHost(navController = navhostController, startDestination = MainRoute.Home.name) {
        composable(MainRoute.Home.name) {
            HomeScreen(navhostController = navhostController)
        }
        composable(MainRoute.Settings.name) {
            SettingsScreen(navhostController = navhostController)
        }
    }
}