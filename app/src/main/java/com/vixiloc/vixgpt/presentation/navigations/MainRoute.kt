package com.vixiloc.vixgpt.presentation.navigations

sealed class MainRoute(val name: String) {
    object Home : MainRoute("home")
    object Settings : MainRoute("settings")
}