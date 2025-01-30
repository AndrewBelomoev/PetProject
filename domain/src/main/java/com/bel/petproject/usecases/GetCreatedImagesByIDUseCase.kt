package com.bel.petproject.usecases

import com.bel.petproject.models.creationResponse.CreationResponse
import com.bel.petproject.repositories.ImagesRemoteRepository

class GetCreatedImagesByIDUseCase(private val repository: ImagesRemoteRepository) {
    suspend operator fun invoke(id: Long): Result<CreationResponse> {
        return repository.getCreatedImagesByID(id)
    }
}

