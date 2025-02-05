package com.bel.petproject.repositories

import com.bel.petproject.models.imageCard.GeneratedImageDetails

interface ImagesLocalRepository {

    suspend fun addGeneratedImageToDatabase(generatedImageDetails: GeneratedImageDetails): Result<Unit>

    suspend fun getAllGeneratedImagesFromDatabase(): Result<List<GeneratedImageDetails>>

    suspend fun deleteGeneratedImageFromDatabase(generatedImageDetails: GeneratedImageDetails): Result<Unit>

}