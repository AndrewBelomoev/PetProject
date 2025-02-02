package com.bel.petproject.repositories

import com.bel.petproject.models.creationResponse.ImageGenerationParameters
import com.bel.petproject.models.creationResponse.GeneratedImageDetails

interface ImagesRemoteRepository {

    suspend fun createNewImages(request: ImageGenerationParameters): Result<GeneratedImageDetails>

    suspend fun getCreatedImagesByID(id: Long): Result<GeneratedImageDetails>

}