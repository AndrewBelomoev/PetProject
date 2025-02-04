package com.bel.petproject.usecases.remote

import com.bel.petproject.models.creationResponse.ImageGenerationParameters
import com.bel.petproject.models.creationResponse.GeneratedImageDetails
import com.bel.petproject.repositories.ImagesRemoteRepository

class CreteNewImagesUseCase(private val repository: ImagesRemoteRepository) {

    suspend operator fun invoke(request: ImageGenerationParameters): Result<GeneratedImageDetails> {
        return repository.createNewImages(request)
    }

}