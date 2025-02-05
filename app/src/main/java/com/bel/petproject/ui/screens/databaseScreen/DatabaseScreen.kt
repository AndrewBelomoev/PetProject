package com.bel.petproject.ui.screens.databaseScreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bel.petproject.ui.navigation.Screen
import com.bel.petproject.ui.screens.SharedViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DatabaseScreen(navController: NavHostController) {
    val viewModel: DbViewModel = koinViewModel()
    val sharedViewModel: SharedViewModel = koinViewModel()

    val images by viewModel.images.collectAsState()

    val context = LocalContext.current

    Column {
        LazyColumn {
            items(images) { imageDetails ->
                Log.d("Images?", "my image is: ${imageDetails.images}")
                SavedImageDetailsViewHolder(
                    generatedImageDetails = imageDetails,
                    onCardClick = {
                        sharedViewModel.setImageDetails(it)
                        navController.navigate(Screen.Details.route)
                    },
                    onImageClick = {
                        val imageUrl = it.url
                        sharedViewModel.setImageUrl(imageUrl ?: "")
                        navController.navigate(Screen.FullScreenImage.route)
                    },
                    onImageLongClick = { /* Обработка долгого клика по изображению */ }
                )
            }
        }
    }

}

@Preview
@Composable
fun DatabaseScreenPreview() {
    DatabaseScreen(navController = rememberNavController())
}
