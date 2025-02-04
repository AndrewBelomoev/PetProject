package com.bel.petproject.ui.screens.detailsScreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bel.petproject.ui.screens.SharedViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen() {
    val sharedViewModel: SharedViewModel = koinViewModel()
    val sharedData by sharedViewModel.imageDetails.collectAsState()
    Log.d("DetailsScreenSharedData", "your data: $sharedData")

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