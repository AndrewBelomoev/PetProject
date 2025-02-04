package com.bel.petproject.data.repositories

import com.bel.petproject.data.database.room.GeneratedImageDao
import com.bel.petproject.data.models.mapper.toDomainModel
import com.bel.petproject.data.models.mapper.toEntityModel
import com.bel.petproject.models.creationResponse.GeneratedImageDetails
import com.bel.petproject.repositories.ImagesLocalRepository

internal class ImagesLocalReposImpl(private val dao: GeneratedImageDao) :
    ImagesLocalRepository {

    override suspend fun addGeneratedImageToDatabase(generatedImageDetails: GeneratedImageDetails) =
        runCatching {
            dao.addGeneratedImage(generatedImageDbEntity = generatedImageDetails.toEntityModel())
        }

    override suspend fun getAllGeneratedImagesFromDatabase() = runCatching {
        dao.getAllGeneratedImages()
    }.map { listGeneratedImageDbEntity ->
        listGeneratedImageDbEntity.map { generatedImageDbEntity ->
            generatedImageDbEntity.toDomainModel()
        }
    }

    override suspend fun deleteGeneratedImageFromDatabase(generatedImageDetails: GeneratedImageDetails) =
        runCatching {
            dao.deleteGeneratedImage(generatedImageDetails.toEntityModel())
        }

}