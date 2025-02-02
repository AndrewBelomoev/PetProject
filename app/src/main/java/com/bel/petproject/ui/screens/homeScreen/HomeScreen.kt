package com.bel.petproject.ui.screens.homeScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = koinViewModel()
    val creationResponse by viewModel.creationResponse.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCreation(109713) // Пример ID
    }

    if (creationResponse == null) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Null")
            CircularProgressIndicator(Modifier.scale(2f))
        }
    } else {
        creationResponse?.let {
            CreationCardViewHolder(creation = it, onCardClick = {}, onImageClick = {}) {

            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}