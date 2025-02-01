package com.bel.petproject.ui.screens.detailsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bel.petproject.models.creationResponse.CreationResponse
import com.bel.petproject.usecases.CreteNewImagesUseCase
import com.bel.petproject.usecases.GetCreatedImagesByIDUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val creteNewImagesUseCase: CreteNewImagesUseCase,
    private val getCreatedImagesByIDUseCase: GetCreatedImagesByIDUseCase
) : ViewModel() {
    private val _creationResponse = MutableStateFlow<CreationResponse?>(null)
    val creationResponse: StateFlow<CreationResponse?> = _creationResponse

    fun fetchImagesByID(id: Long) {
        viewModelScope.launch {
            getCreatedImagesByIDUseCase(id).map {
                val data = it
                println(data.toString())
            }
            val result = getCreatedImagesByIDUseCase(id)
            if (result.isSuccess) {
                _creationResponse.value = result.getOrNull()
            } else {
                _creationResponse.value = null
            }
        }
    }

}



