package com.bel.petproject.usecases.local

import com.bel.petproject.models.imageCard.GeneratedImageDetails
import com.bel.petproject.repositories.ImagesLocalRepository
import kotlinx.coroutines.flow.Flow

class GetAllGeneratedImagesFromDatabaseUseCase(private val repository: ImagesLocalRepository) {

    suspend operator fun invoke(): Result<Flow<List<GeneratedImageDetails>>> {
        return repository.getAllGeneratedImagesFromDatabase()
    }

}