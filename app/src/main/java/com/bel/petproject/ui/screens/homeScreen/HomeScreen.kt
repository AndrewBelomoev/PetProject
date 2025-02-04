package com.bel.petproject.ui.screens.homeScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
//        viewModel.loadGeneratedImageDetails(109713) // ERROR
        viewModel.loadGeneratedImageDetails(110141)
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
                generatedImageDetails = state.data,
                onCardClick = {
                    sharedViewModel.setImageDetails(state.data)
                    Log.d("Send data", "data: ${state.data}")
                    navController.navigate(Screen.Details.route)
                },
                onImageClick = {
                    val imageUrl = it.url
                    sharedViewModel.setImageUrl(imageUrl ?: "")
                    navController.navigate(Screen.FullScreenImage.route)
                }
            ) { }
        }

        is LceState.Error -> {
            val isNetworkError =
                state.throwable.message?.contains("timeout") == true || state.throwable.message?.contains(
                    "Unable to resolve host"
                ) == true

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Error Icon",
                        tint = Color.Red,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = if (isNetworkError) "Проверьте соединение с интернетом" else "Error: ${state.throwable.message}",
                        color = Color.Red,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { viewModel.loadGeneratedImageDetails(110141) }) {
                        Text(text = "Повторить")
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}