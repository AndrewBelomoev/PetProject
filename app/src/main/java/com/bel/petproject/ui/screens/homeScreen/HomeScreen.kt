package com.bel.petproject.ui.screens.homeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bel.petproject.ui.navigation.Screen
import com.bel.petproject.ui.screens.SharedViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: HomeViewModel = koinViewModel()
    val sharedViewModel: SharedViewModel = koinViewModel()

    val generatedImageDetails by viewModel.generatedImageDetails.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadGeneratedImageDetails(109713)
    }

    when (val state = generatedImageDetails) {
        is LceState.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        is LceState.Content -> {
            CreationCardViewHolder(
                creation = state.data,
                onCardClick = {

                    navController.navigate(Screen.Details.route)
                },
                onImageClick = {
                }
            ) { }
        }

        is LceState.Error -> {
            // Показать сообщение об ошибке
            Text(text = "Error: ${state.throwable.message}")
        }
    }

}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}