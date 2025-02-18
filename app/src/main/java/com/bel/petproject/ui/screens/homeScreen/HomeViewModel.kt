package com.bel.petproject.ui.screens.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bel.petproject.model.LceState
import com.bel.petproject.models.imageCard.GeneratedImageDetails
import com.bel.petproject.models.imageCard.Image
import com.bel.petproject.models.imageCard.ImageGenerationParameters
import com.bel.petproject.usecases.local.SaveGeneratedImageToDatabaseUseCase
import com.bel.petproject.usecases.local.SaveImageToGalleryUseCase
import com.bel.petproject.usecases.remote.CreteNewImagesUseCase
import com.bel.petproject.usecases.remote.GetCreatedImagesByIDUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val creteNewImagesUseCase: CreteNewImagesUseCase,
    private val getCreatedImagesByIDUseCase: GetCreatedImagesByIDUseCase,
    private val saveImageCardUseCase: SaveGeneratedImageToDatabaseUseCase,
    private val saveImageToGalleryUseCase: SaveImageToGalleryUseCase
) : ViewModel() {

    private val _generatedImageDetails =
        MutableStateFlow<LceState<GeneratedImageDetails>>(LceState.Loading)
    val generatedImageDetails: StateFlow<LceState<GeneratedImageDetails>> = _generatedImageDetails

    private val _generationStatus = MutableStateFlow("")
    val generationStatus: StateFlow<String> get() = _generationStatus

    private val _saveImageToGalleryResult = MutableStateFlow<Boolean?>(null)
    val saveImageToGalleryResult: StateFlow<Boolean?> get() = _saveImageToGalleryResult

    fun createNewImages(request: ImageGenerationParameters) {
        viewModelScope.launch {
            creteNewImagesUseCase.invoke(request).onSuccess { generatedImageDetails ->
                _generationStatus.value = generatedImageDetails.status.toString()
                val id = generatedImageDetails.id
                checkGeneratedImageStatus(id)
            }.onFailure {
                _generatedImageDetails.value = LceState.Error(it)
            }
        }
    }

    private fun checkGeneratedImageStatus(imageId: Long) {
        viewModelScope.launch {
            while (true) {
                getCreatedImagesByIDUseCase(imageId).onSuccess { generatedImageDetails ->
                    if (generatedImageDetails.status == "completed") {
                        _generationStatus.value = generatedImageDetails.status.toString()
                        _generatedImageDetails.value = LceState.Content(generatedImageDetails)
                        return@launch
                    } else {
                        _generationStatus.value = generatedImageDetails.status.toString()
                        delay(5000)

                    }
                }.onFailure {
                    _generatedImageDetails.value = LceState.Error(it)
                    return@launch
                }
            }
        }
    }

    fun saveImageCard(imageCard: GeneratedImageDetails) {
        viewModelScope.launch {
            saveImageCardUseCase(imageCard)
        }
    }

    fun saveImageToGallery(url: String) {
        viewModelScope.launch {
            val result = saveImageToGalleryUseCase(url)
            _saveImageToGalleryResult.value = result
        }
    }

    fun deleteImage(image: Image) {
        val currentState = _generatedImageDetails.value
        if (currentState is LceState.Content) {
            val updatedImages = currentState.data.images?.toMutableList()?.apply {
                remove(image)
            }
            _generatedImageDetails.value = LceState.Content(
                currentState.data.copy(images = updatedImages)
            )
        }
    }
}
