package com.bel.petproject.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(val name: String, val icon: ImageVector, val route: String)

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    currentRoute: String,
    onItemClick: (BottomNavItem) -> Unit
) {
    NavigationBar {
        items.forEach { bottomNavItem ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = bottomNavItem.icon,
                        contentDescription = bottomNavItem.name
                    )
                },
                label = { Text(text = bottomNavItem.name) },
                selected = bottomNavItem.route == currentRoute,
                onClick = { onItemClick(bottomNavItem) }
            )
        }
    }
}
