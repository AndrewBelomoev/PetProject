package com.bel.petproject.repositories

import com.bel.petproject.models.creationResponse.CreationRequest
import com.bel.petproject.models.creationResponse.CreationResponse

interface ImagesRemoteRepository {

    suspend fun createNewImages(request: CreationRequest): Result<CreationResponse>

    suspend fun getCreatedImagesByID(id: Long): Result<CreationResponse>

}