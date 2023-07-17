package com.vixiloc.vixgpt.domain.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vixiloc.vixgpt.presentation.sreens.Chat
import com.vixiloc.vixgpt.presentation.sreens.Splash

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.Splash.route) {
        composable(route = Destination.Splash.route) {
            Splash(navController)
        }
        composable(route = Destination.Chat.route) {
            Chat()
        }
    }
}

sealed class Destination(val route: String) {
    object Chat : Destination(route = "chat")
    object Splash : Destination(route = "splash")
}