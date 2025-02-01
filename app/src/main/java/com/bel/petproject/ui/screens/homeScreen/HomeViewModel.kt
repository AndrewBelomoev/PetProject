package com.bel.petproject.ui.screens.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bel.petproject.models.creationResponse.CreationResponse
import com.bel.petproject.models.creationResponse.Image
import com.bel.petproject.usecases.CreteNewImagesUseCase
import com.bel.petproject.usecases.GetCreatedImagesByIDUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
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

    val creation = CreationResponse(
        id = 1L,
        status = "Success",
        prompt = "Generate a landscape image",
        negativePrompt = "Do not include any buildings",
        width = 1920L,
        height = 1080L,
        highResolution = true,
        seed = 123456L,
        steps = 100L,
        model = "StableDiffusion",
        initialImage = null,
        initialImageMode = null,
        initialImageStrength = null,
        createdAt = "2025-02-01T18:01:00",
        updatedAt = "2025-02-01T18:01:00",
        images = listOf(
            Image(id = 1, url = "https://images.pexels.com/photos/674010/pexels-photo-674010.jpeg?cs=srgb&dl=pexels-anjana-c-169994-674010.jpg&fm=jpg"),
            Image(id = 1, url = "https://images.pexels.com/photos/674010/pexels-photo-674010.jpeg?cs=srgb&dl=pexels-anjana-c-169994-674010.jpg&fm=jpg"),
            Image(id = 1, url = "https://images.pexels.com/photos/674010/pexels-photo-674010.jpeg?cs=srgb&dl=pexels-anjana-c-169994-674010.jpg&fm=jpg"),
            Image(id = 1, url = "https://images.pexels.com/photos/674010/pexels-photo-674010.jpeg?cs=srgb&dl=pexels-anjana-c-169994-674010.jpg&fm=jpg"),
            Image(id = 1, url = "https://images.pexels.com/photos/674010/pexels-photo-674010.jpeg?cs=srgb&dl=pexels-anjana-c-169994-674010.jpg&fm=jpg"),
            Image(id = 1, url = "https://images.pexels.com/photos/674010/pexels-photo-674010.jpeg?cs=srgb&dl=pexels-anjana-c-169994-674010.jpg&fm=jpg"),
            Image(id = 1, url = "https://images.pexels.com/photos/674010/pexels-photo-674010.jpeg?cs=srgb&dl=pexels-anjana-c-169994-674010.jpg&fm=jpg"),
            Image(id = 1, url = "https://images.pexels.com/photos/674010/pexels-photo-674010.jpeg?cs=srgb&dl=pexels-anjana-c-169994-674010.jpg&fm=jpg"),
            Image(id = 1, url = "https://images.pexels.com/photos/674010/pexels-photo-674010.jpeg?cs=srgb&dl=pexels-anjana-c-169994-674010.jpg&fm=jpg"),
            Image(id = 1, url = "https://images.pexels.com/photos/674010/pexels-photo-674010.jpeg?cs=srgb&dl=pexels-anjana-c-169994-674010.jpg&fm=jpg"),
            Image(id = 1, url = "https://images.pexels.com/photos/674010/pexels-photo-674010.jpeg?cs=srgb&dl=pexels-anjana-c-169994-674010.jpg&fm=jpg"),

        ),
        expired = false
    )

}