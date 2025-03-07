package com.bel.petproject.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import com.bel.petproject.models.imageCard.GeneratedImageDetails
import com.bel.petproject.models.imageCard.ImageGenerationParameters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedViewModel : ViewModel() {
    private val _imageDetails = MutableStateFlow<GeneratedImageDetails?>(null)
    val imageDetails: StateFlow<GeneratedImageDetails?> get() = _imageDetails

    private val _imageUrl = MutableStateFlow<String?>(null)
    val imageUrl: StateFlow<String?> = _imageUrl.asStateFlow()

    private val _generationParameters = MutableStateFlow<ImageGenerationParameters?>(null)
    val generationParameters: StateFlow<ImageGenerationParameters?> get() = _generationParameters

    fun setImageDetails(data: GeneratedImageDetails) {
        _imageDetails.value = data
        Log.d("SharedVMData", "Shared data: ${_imageDetails.value}")
    }

    fun setImageUrl(url: String) {
        _imageUrl.value = url
    }

    fun setGenerationParameters(parameters: ImageGenerationParameters) {
        _generationParameters.value = parameters
        Log.d("GenerationParams", "Parameters: $parameters")
    }

}