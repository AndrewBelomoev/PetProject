package com.bel.petproject.usecases.local

import com.bel.petproject.models.creationResponse.GeneratedImageDetails
import com.bel.petproject.repositories.ImagesLocalRepository

class GetAllGeneratedImagesFromDatabaseUseCase(private val repository: ImagesLocalRepository) {

    suspend operator fun invoke(): Result<List<GeneratedImageDetails>> {
        return repository.getAllGeneratedImagesFromDatabase()
    }

}