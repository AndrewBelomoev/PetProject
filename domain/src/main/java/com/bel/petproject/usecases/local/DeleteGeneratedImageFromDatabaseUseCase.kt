package com.bel.petproject.usecases.local

import com.bel.petproject.models.imageCard.GeneratedImageDetails
import com.bel.petproject.repositories.ImagesLocalRepository

class DeleteGeneratedImageFromDatabaseUseCase(private val repository: ImagesLocalRepository) {
    suspend operator fun invoke(generatedImageDetails: GeneratedImageDetails): Result<Unit> {
        return repository.deleteGeneratedImageFromDatabase(generatedImageDetails)
    }
}