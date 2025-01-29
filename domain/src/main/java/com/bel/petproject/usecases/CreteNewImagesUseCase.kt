package com.bel.petproject.usecases

import com.bel.petproject.models.creationResponse.CreationRequest
import com.bel.petproject.models.creationResponse.CreationResponse
import com.bel.petproject.repositories.ImagesRemoteRepository

class CreteNewImagesUseCase(private val repository: ImagesRemoteRepository) {

    suspend operator fun invoke(request: CreationRequest): Result<CreationResponse> {
        return repository.createNewImages(request)
    }

}