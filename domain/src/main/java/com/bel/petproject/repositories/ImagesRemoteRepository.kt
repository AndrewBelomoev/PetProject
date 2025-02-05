package com.bel.petproject.repositories

import com.bel.petproject.models.imageCard.ImageGenerationParameters
import com.bel.petproject.models.imageCard.GeneratedImageDetails

interface ImagesRemoteRepository {

    suspend fun createNewImages(request: ImageGenerationParameters): Result<GeneratedImageDetails>

    suspend fun getCreatedImagesByID(id: Long): Result<GeneratedImageDetails>

}