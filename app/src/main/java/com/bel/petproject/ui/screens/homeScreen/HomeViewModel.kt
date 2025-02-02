package com.bel.petproject.ui.screens.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bel.petproject.models.creationResponse.CreationResponse
import com.bel.petproject.models.creationResponse.Image
import com.bel.petproject.usecases.CreteNewImagesUseCase
import com.bel.petproject.usecases.GetCreatedImagesByIDUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val creteNewImagesUseCase: CreteNewImagesUseCase,
    private val getCreatedImagesByIDUseCase: GetCreatedImagesByIDUseCase
) : ViewModel() {
    private val _creationResponse = MutableStateFlow<CreationResponse?>(null)
    val creationResponse: StateFlow<CreationResponse?> = _creationResponse

    fun loadCreation(id: Long) {
        viewModelScope.launch {
            getCreatedImagesByIDUseCase.invoke(id).onSuccess {
                _creationResponse.value = it
            }.onFailure {
                // Handle the error
            }
        }
    }

}

