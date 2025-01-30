package com.bel.petproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bel.petproject.ui.screens.detailsScreen.DetailsScreen
import com.bel.petproject.ui.screens.homeScreen.HomeScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ){
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route
        ){
            DetailsScreen(navController = navController)
        }
    }
}