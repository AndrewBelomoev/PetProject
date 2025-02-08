package com.bel.petproject.ui.screens.applicationScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bel.petproject.ui.navigation.BottomNavItem
import com.bel.petproject.ui.navigation.BottomNavigationBar
import com.bel.petproject.ui.navigation.Screen
import com.bel.petproject.ui.navigation.SetupNavGraph

private val bottomNavItems = listOf(
    BottomNavItem("Create", Icons.Default.Create, Screen.Create.route),
    BottomNavItem("Database", Icons.Default.AddCircle, Screen.Database.route),
    BottomNavItem("Settings", Icons.Default.Settings, Screen.Settings.route)
)

@Composable
fun ApplicationScreen() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = bottomNavItems,
                currentRoute = currentRoute ?: Screen.Home.route,
                onItemClick = { navItem ->
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            SetupNavGraph(navController = navController)
        }
    }
}
