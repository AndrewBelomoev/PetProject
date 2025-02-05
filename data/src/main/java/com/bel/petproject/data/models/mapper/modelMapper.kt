package com.bel.petproject.data.models.mapper

import com.bel.petproject.data.models.GeneratedImageDbEntity
import com.bel.petproject.data.models.ImageGenerationParametersDTO
import com.bel.petproject.data.models.GeneratedImageDetailsDTO
import com.bel.petproject.data.models.ImageDTO
import com.bel.petproject.data.models.ImageDbEntity
import com.bel.petproject.models.imageCard.ImageGenerationParameters
import com.bel.petproject.models.imageCard.GeneratedImageDetails
import com.bel.petproject.models.imageCard.Image

fun ImageGenerationParametersDTO.toDomainModel(): ImageGenerationParameters {
    return ImageGenerationParameters(
        model = model,
        aspectRatio = aspectRatio,
        highResolution = highResolution,
        images = images,
        steps = steps,
        prompt = prompt,
        negativePrompt = negativePrompt,
        seed = seed
    )
}

fun ImageGenerationParameters.toDTOModel(): ImageGenerationParametersDTO {
    return ImageGenerationParametersDTO(
        model = model,
        aspectRatio = aspectRatio,
        highResolution = highResolution,
        images = images,
        steps = steps,
        prompt = prompt,
        negativePrompt = negativePrompt,
        seed = seed
    )
}

fun GeneratedImageDetailsDTO.toDomainModel(): GeneratedImageDetails {
    return GeneratedImageDetails(
        id = id,
        status = status,
        prompt = prompt,
        negativePrompt = negativePrompt,
        width = width,
        height = height,
        highResolution = highResolution,
        seed = seed,
        steps = steps,
        model = model,
        initialImage = initialImage,
        initialImageMode = initialImageMode,
        initialImageStrength = initialImageStrength,
        createdAt = createdAt,
        updatedAt = updatedAt,
        images = images,
        expired = expired
    )
}

fun GeneratedImageDetails.toEntityModel(): GeneratedImageDbEntity {
    return GeneratedImageDbEntity(
        id = id,
        status = status,
        prompt = prompt,
        negativePrompt = negativePrompt,
        width = width,
        height = height,
        highResolution = highResolution,
        seed = seed,
        steps = steps,
        model = model,
        initialImage = initialImage,
        initialImageMode = initialImageMode,
        initialImageStrength = initialImageStrength,
        createdAt = createdAt,
        updatedAt = updatedAt,
        images = images?.map { it.toEntityModel() },
        expired = expired
    )
}

fun GeneratedImageDbEntity.toDomainModel(): GeneratedImageDetails {
    return GeneratedImageDetails(
        id = id,
        status = status,
        prompt = prompt,
        negativePrompt = negativePrompt,
        width = width,
        height = height,
        highResolution = highResolution,
        seed = seed,
        steps = steps,
        model = model,
        initialImage = initialImage,
        initialImageMode = initialImageMode,
        initialImageStrength = initialImageStrength,
        createdAt = createdAt,
        updatedAt = updatedAt,
        images = images?.map { it.toDomainModel() },
        expired = expired
    )
}

fun ImageDTO.toDomainModel(): Image {
    return Image(
        id = id,
        url = url
    )
}

fun Image.toDTOModel(): ImageDTO {
    return ImageDTO(
        id = id,
        url = url
    )
}

fun Image.toEntityModel(): ImageDbEntity {
    return ImageDbEntity(
        id = id,
        url = url
    )
}

fun ImageDbEntity.toDomainModel(): Image {
    return Image(
        id = id,
        url = url
    )
}