package com.bel.petproject.usecases

import com.bel.petproject.models.creationResponse.GeneratedImageDetails
import com.bel.petproject.repositories.ImagesRemoteRepository

class GetCreatedImagesByIDUseCase(private val repository: ImagesRemoteRepository) {
    suspend operator fun invoke(id: Long): Result<GeneratedImageDetails> {
        return repository.getCreatedImagesByID(id)
    }
}

