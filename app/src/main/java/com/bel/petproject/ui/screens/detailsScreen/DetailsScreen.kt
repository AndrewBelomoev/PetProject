package com.bel.petproject.ui.screens.detailsScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.rememberAsyncImagePainter
import com.bel.petproject.ui.screens.SharedViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(navController: NavHostController) {
    val sharedViewModel: SharedViewModel = koinViewModel()
    val sharedData by sharedViewModel.imageDetails.collectAsState()
    Log.d("DetailsScreenSharedData", "your data: $sharedData")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier

                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        sharedData?.let { data ->
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "ID: ${data.id}")
                Text(text = "Status: ${data.status}")
                Text(text = "Prompt: ${data.prompt}")
                Text(text = "Negative Prompt: ${data.negativePrompt}")
                Text(text = "Width: ${data.width}")
                Text(text = "Height: ${data.height}")
                Text(text = "High Resolution: ${data.highResolution}")
                Text(text = "Seed: ${data.seed}")
                Text(text = "Steps: ${data.steps}")
                Text(text = "Model: ${data.model}")
            }
        } ?: run {
            Text(text = "No data available")
        }

    }
}