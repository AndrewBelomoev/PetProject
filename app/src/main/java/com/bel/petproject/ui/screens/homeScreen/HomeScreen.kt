package com.bel.petproject.ui.screens.homeScreen

import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bel.petproject.model.LceState
import com.bel.petproject.models.imageCard.Image
import com.bel.petproject.ui.navigation.Screen
import com.bel.petproject.ui.screens.ImageCardViewHolder
import com.bel.petproject.ui.screens.SharedViewModel
import com.bel.petproject.ui.screens.dialog.ShowDeleteConfirmationDialog
import com.bel.petproject.ui.screens.dialog.ShowSaveConfirmationDialog
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: HomeViewModel = koinViewModel()
    val sharedViewModel: SharedViewModel = koinViewModel()

    val context = LocalContext.current

    val generationStatus by viewModel.generationStatus.collectAsState()
    val generationParameters by sharedViewModel.generationParameters.collectAsState()
    val generatedImageDetails by viewModel.generatedImageDetails.collectAsState()

    val saveImageToGalleryResult by viewModel.saveImageToGalleryResult.collectAsState()

    var showDelDialog by remember { mutableStateOf(false) }
    var selectedImageToDel by remember { mutableStateOf<Image?>(null) }

    var showSaveDialog by remember { mutableStateOf(false) }
    var selectedImageToSave by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        // Проверяем, есть ли уже сгенерированные изображения
        if (generatedImageDetails !is LceState.Content) {
            val request = generationParameters!!
            Log.d("generationParameters1", request.toString())
            viewModel.createNewImages(request = request)
        } else {
            Log.d("generationParameters1", "Images already generated, skipping generation.")
        }
    }

    when (val state = generatedImageDetails) {
        is LceState.Loading -> {
            Text(text = "Status: $generationStatus", modifier = Modifier.padding(20.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        is LceState.Content -> {
            Column {
                ImageCardViewHolder(
                    mode = 1,
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
                    },
                    onImageLongClick = { image ->
                        showSaveDialog = true
                        selectedImageToSave = image.url
                    },
                    onSaveButtonClick = {
                        viewModel.saveImageCard(it)
                        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                    },
                    onDetailsButtonClick = {
                        sharedViewModel.setImageDetails(state.data)
                        navController.navigate(Screen.Details.route)
                    },
                    onDelCardButtonClick = {

                    },
                    onDelImageButtonClick = { image ->
                        selectedImageToDel = image
                        showDelDialog = true
                    }

                )
            }
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
                    Button(onClick = {
                        val request = generationParameters
                        if (request != null) {
                            viewModel.createNewImages(request = request)
                        }
                    }) {
                        Text(text = "Повторить")
                    }
                }
            }
        }
    }

    if (showDelDialog && selectedImageToDel != null) {
        ShowDeleteConfirmationDialog(
            onConfirm = {
                viewModel.deleteImage(selectedImageToDel!!)
                Toast.makeText(context, "Изображение удалено", Toast.LENGTH_SHORT).show()
                showDelDialog = false
            },
            onDismiss = { showDelDialog = false }
        )
    }
    if (showSaveDialog && selectedImageToSave != null) {
        ShowSaveConfirmationDialog(
            onConfirm = {
                viewModel.saveImageToGallery(selectedImageToSave!!)
                saveImageToGalleryResult?.let { result ->
                    if (result) {
                        Toast.makeText(
                            context,
                            "Image saved successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(context, "Failed to save image", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                showSaveDialog = false
            },
            onDismiss = {
                showSaveDialog = false
            }
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}


