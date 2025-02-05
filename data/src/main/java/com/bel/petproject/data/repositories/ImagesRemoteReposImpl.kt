package com.bel.petproject.data.repositories

import android.util.Log
import com.bel.petproject.data.api.starryAiAPI.StarryAiAPI
import com.bel.petproject.data.models.mapper.toDTOModel
import com.bel.petproject.data.models.mapper.toDomainModel
import com.bel.petproject.models.imageCard.ImageGenerationParameters
import com.bel.petproject.repositories.ImagesRemoteRepository

class ImagesRemoteReposImpl(private val api: StarryAiAPI) : ImagesRemoteRepository {

    override suspend fun createNewImages(request: ImageGenerationParameters) = runCatching {
        api.newCreation(request = request.toDTOModel()).toDomainModel()
    }.onFailure {
        Log.e("API ERROR", it.message ?: "Unknown error")
    }

    override suspend fun getCreatedImagesByID(id: Long) = runCatching {
        api.getCreationByID(id = id).toDomainModel()
    }.onFailure {
        Log.e("API ERROR", it.message ?: "Unknown error")
    }

}