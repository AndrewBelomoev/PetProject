package com.bel.petproject.ui.screens.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bel.petproject.models.creationResponse.GeneratedImageDetails
import com.bel.petproject.usecases.remote.CreteNewImagesUseCase
import com.bel.petproject.usecases.remote.GetCreatedImagesByIDUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val creteNewImagesUseCase: CreteNewImagesUseCase,
    private val getCreatedImagesByIDUseCase: GetCreatedImagesByIDUseCase
) : ViewModel() {
    private val _generatedImageDetails = MutableStateFlow<LceState<GeneratedImageDetails>>(LceState.Loading)
    val generatedImageDetails: StateFlow<LceState<GeneratedImageDetails>> = _generatedImageDetails

    fun loadGeneratedImageDetails(id: Long) {
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

}

sealed class LceState<out T> {
    data object Loading : LceState<Nothing>()
    data class Content<out T>(val data: T) : LceState<T>()
    data class Error(val throwable: Throwable) : LceState<Nothing>()
}

