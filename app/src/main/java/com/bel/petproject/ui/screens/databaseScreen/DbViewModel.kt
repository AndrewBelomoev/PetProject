package com.bel.petproject.ui.screens.databaseScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bel.petproject.model.LceState
import com.bel.petproject.models.imageCard.GeneratedImageDetails
import com.bel.petproject.usecases.local.DeleteGeneratedImageFromDatabaseUseCase
import com.bel.petproject.usecases.local.GetAllGeneratedImagesFromDatabaseUseCase
import com.bel.petproject.usecases.local.SaveImageToGalleryUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DbViewModel(
    private val getAllImagesUseCase: GetAllGeneratedImagesFromDatabaseUseCase,
    private val delete: DeleteGeneratedImageFromDatabaseUseCase,
    private val saveImageToGalleryUseCase: SaveImageToGalleryUseCase
) : ViewModel() {

    private val _lceDatabaseFlow =
        MutableStateFlow<LceState<Flow<List<GeneratedImageDetails>>>>(LceState.Loading)
    val lceDatabaseFlow: Flow<LceState<Flow<List<GeneratedImageDetails>>>> =
        _lceDatabaseFlow.asStateFlow()

    private val _saveImageToGalleryResult = MutableStateFlow<Boolean?>(null)
    val saveImageToGalleryResult: StateFlow<Boolean?> get() = _saveImageToGalleryResult

    init {
        getImages()
    }

    private fun getImages() {
        viewModelScope.launch {
            getAllImagesUseCase.invoke().fold(
                onSuccess = {
                    _lceDatabaseFlow.tryEmit(LceState.Content(it))
                },
                onFailure = {

                }
            )
        }
    }

    fun saveImageToGallery(url: String) {
        viewModelScope.launch {
            val result = saveImageToGalleryUseCase(url)
            _saveImageToGalleryResult.value = result
        }
    }

    fun deleteCard(generatedImageDetails: GeneratedImageDetails) {
        viewModelScope.launch {
            delete(generatedImageDetails).onSuccess {

            }
                .onFailure {

                }
        }
    }
}