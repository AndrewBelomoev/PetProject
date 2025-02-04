package com.bel.petproject.ui.screens.databaseScreen

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bel.petproject.data.models.ImageDbEntity
import com.bel.petproject.models.creationResponse.GeneratedImageDetails
import com.bel.petproject.usecases.local.GetAllGeneratedImagesFromDatabaseUseCase
import com.bel.petproject.usecases.local.SaveGeneratedImageToDatabaseUseCase
import com.bel.petproject.usecases.remote.GetCreatedImagesByIDUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DbViewModel(
    private val saveImageUseCase: SaveGeneratedImageToDatabaseUseCase,
    private val getAllImagesUseCase: GetAllGeneratedImagesFromDatabaseUseCase
) : ViewModel() {

    private val _images = MutableStateFlow<List<GeneratedImageDetails>>(emptyList())
    val images: StateFlow<List<GeneratedImageDetails>> get() = _images

    init {
        getImages()
    }

    fun save(generatedImage: GeneratedImageDetails) {
        viewModelScope.launch {
            saveImageUseCase(generatedImage)
        }
    }

    fun getImages() {
        viewModelScope.launch {
            val result = getAllImagesUseCase()
            result.onSuccess { _images.value = it }
        }
    }
}