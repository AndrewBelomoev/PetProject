package com.bel.petproject.ui.screens.databaseScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bel.petproject.models.imageCard.GeneratedImageDetails
import com.bel.petproject.usecases.local.GetAllGeneratedImagesFromDatabaseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DbViewModel(
    private val getAllImagesUseCase: GetAllGeneratedImagesFromDatabaseUseCase
) : ViewModel() {

    private val _images = MutableStateFlow<List<GeneratedImageDetails>>(emptyList())
    val images: StateFlow<List<GeneratedImageDetails>> get() = _images

    init {
        getImages()
    }

    private fun getImages() {
        viewModelScope.launch {
            val result = getAllImagesUseCase()
            result.onSuccess { _images.value = it }
        }
    }
}