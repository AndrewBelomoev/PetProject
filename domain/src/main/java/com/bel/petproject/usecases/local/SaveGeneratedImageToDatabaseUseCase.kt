package com.bel.petproject.usecases.local

import com.bel.petproject.models.creationResponse.GeneratedImageDetails
import com.bel.petproject.repositories.ImagesLocalRepository

class SaveGeneratedImageToDatabaseUseCase(private val repository: ImagesLocalRepository) {

    suspend operator fun invoke(generatedImageDetails: GeneratedImageDetails): Result<Unit> {
        return repository.addGeneratedImageToDatabase(generatedImageDetails)
    }

}