package com.bel.petproject.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen(route = "home_screen")
    data object Details : Screen(route = "details_screen")
    data object Settings : Screen(route = "settings_screen")
    data object MyScreen : Screen(route = "my_screen")
}