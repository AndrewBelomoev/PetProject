package com.bel.petproject.ui.screens.databaseScreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.bel.petproject.model.LceState
import com.bel.petproject.models.imageCard.GeneratedImageDetails
import com.bel.petproject.ui.navigation.Screen
import com.bel.petproject.ui.screens.ImageCardViewHolder
import com.bel.petproject.ui.screens.SharedViewModel
import com.bel.petproject.ui.screens.dialog.ShowDeleteConfirmationDialog
import com.bel.petproject.ui.screens.dialog.ShowSaveConfirmationDialog
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel

@Composable
fun DatabaseScreen(navController: NavHostController) {
    val viewModel: DbViewModel = koinViewModel()
    val sharedViewModel: SharedViewModel = koinViewModel()

    val lceState by viewModel.lceDatabaseFlow.collectAsStateWithLifecycle(
        initialValue = LceState.Loading,
        lifecycle = LocalLifecycleOwner.current.lifecycle
    )

    val saveImageToGalleryResult by viewModel.saveImageToGalleryResult.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var selectedCard by remember { mutableStateOf<GeneratedImageDetails?>(null) }

    var showSaveDialog by remember { mutableStateOf(false) }
    var selectedImageToSave by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current

    Column {
        when (lceState) {
            is LceState.Loading -> {
                // Показываем индикатор загрузки

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }

            }

            is LceState.Content -> {
                val images by (lceState as LceState.Content<Flow<List<GeneratedImageDetails>>>).data.collectAsStateWithLifecycle(
                    emptyList()
                )
                LazyColumn {
                    items(images) { imageDetails ->
                        Log.d("Images?", "my image is: ${imageDetails.images}")
                        ImageCardViewHolder(
                            mode = 2,
                            generatedImageDetails = imageDetails,
                            onCardClick = {
                                sharedViewModel.setImageDetails(it)
                                navController.navigate(Screen.Details.route)
                            },
                            onDelCardButtonClick = {
                                selectedCard = it
                                showDialog = true
                            },
                            onImageClick = {
                                val imageUrl = it.url
                                sharedViewModel.setImageUrl(imageUrl ?: "")
                                navController.navigate(Screen.FullScreenImage.route)
                            },
                            onImageLongClick = {
                                showSaveDialog = true
                                selectedImageToSave = it.url
                            },
                            onSaveButtonClick = {},
                            onDetailsButtonClick = {},
                            onDelImageButtonClick = {}
                        )
                    }
                }
            }

            is LceState.Error -> {
                // Показываем сообщение об ошибке
            }

        }
        if (showDialog && selectedCard != null) {
            ShowDeleteConfirmationDialog(
                onConfirm = {
                    viewModel.deleteCard(selectedCard!!)
                    Toast.makeText(context, "Удалено", Toast.LENGTH_SHORT).show()
                    showDialog = false
                },
                onDismiss = { showDialog = false }
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
                }, onDismiss = {
                    showSaveDialog = false
                }
            )
        }
    }
}