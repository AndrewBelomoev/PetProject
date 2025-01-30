package com.bel.petproject.data.repositories

import com.bel.petproject.data.api.starryAiAPI.StarryAiAPI
import com.bel.petproject.data.models.mapper.toDTOModel
import com.bel.petproject.data.models.mapper.toDomainModel
import com.bel.petproject.models.creationResponse.CreationRequest
import com.bel.petproject.repositories.ImagesRemoteRepository

class ImagesRemoteReposImpl(private val api: StarryAiAPI) : ImagesRemoteRepository {

    override suspend fun createNewImages(request: CreationRequest) = runCatching {
        api.newCreation(request = request.toDTOModel()).toDomainModel()
    }

    override suspend fun getCreatedImagesByID(id: Long) = runCatching {
        api.getCreationByID(id = id).toDomainModel()
    }

}