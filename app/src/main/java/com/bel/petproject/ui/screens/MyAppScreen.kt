package com.bel.petproject.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppScreen(navController: NavController) {

    var selectedOrientation by remember { mutableStateOf("square") }
    val orientations = listOf("square", "landscape", "small portrait", "portrait", "wide")
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Image generation") })
        }
    ) { innerPadding ->
        Column(
            Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Выбрана ориентация: $selectedOrientation")
            Button(onClick = { expanded = !expanded }) {
                Text("Выбрать ориентацию")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(200.dp)
            ) {
                orientations.forEach { orientation ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOrientation = orientation
                            expanded = false
                        },
                        text = { Text(orientation) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MyAppScreenPreview() {
    MyAppScreen(
        navController = rememberNavController()
    )
}
