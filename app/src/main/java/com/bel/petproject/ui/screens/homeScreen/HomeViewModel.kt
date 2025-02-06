package com.bel.petproject.ui.screens.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bel.petproject.models.imageCard.GeneratedImageDetails
import com.bel.petproject.models.imageCard.Image
import com.bel.petproject.models.imageCard.ImageGenerationParameters
import com.bel.petproject.usecases.local.SaveGeneratedImageToDatabaseUseCase
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
) : ViewModel() {
    private val _generatedImageDetails =
        MutableStateFlow<LceState<GeneratedImageDetails>>(LceState.Loading)
    val generatedImageDetails: StateFlow<LceState<GeneratedImageDetails>> = _generatedImageDetails

    private val _generationStatus = MutableStateFlow<String>("")
    val generationStatus: StateFlow<String> get() = _generationStatus

    fun createNewImages(request: ImageGenerationParameters) {
        viewModelScope.launch {
            creteNewImagesUseCase.invoke(request).onSuccess { generatedImageDetails ->
                val id = generatedImageDetails.id
                Log.d("FindId", id.toString())
                loadGeneratedImageCardById(id)
            }.onFailure {

            }
        }
    }

    fun loadGeneratedImageCardById(id: Long) {
        if (_generatedImageDetails.value !is LceState.Content) {
            viewModelScope.launch {
                _generatedImageDetails.value = LceState.Loading
                getCreatedImagesByIDUseCase.invoke(id).onSuccess {
                    _generatedImageDetails.value = LceState.Content(it)
                }.onFailure {
                    _generatedImageDetails.value = LceState.Error(it)
                }
            }
        }
    }

    fun saveImageCard(imageCard: GeneratedImageDetails) {
        viewModelScope.launch {
            saveImageCardUseCase(imageCard)
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

sealed class LceState<out T> {
    data object Loading : LceState<Nothing>()
    data class Content<out T>(val data: T) : LceState<T>()
    data class Error(val throwable: Throwable) : LceState<Nothing>()
}

