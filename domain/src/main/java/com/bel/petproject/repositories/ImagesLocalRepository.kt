package com.bel.petproject.repositories

import com.bel.petproject.models.imageCard.GeneratedImageDetails
import kotlinx.coroutines.flow.Flow

interface ImagesLocalRepository {

    suspend fun addGeneratedImageToDatabase(generatedImageDetails: GeneratedImageDetails): Result<Unit>

    suspend fun getAllGeneratedImagesFromDatabase(): Result<Flow<List<GeneratedImageDetails>>>

    suspend fun deleteGeneratedImageFromDatabase(generatedImageDetails: GeneratedImageDetails): Result<Unit>

}