package com.bel.petproject.data.models.mapper

import com.bel.petproject.data.models.CreationRequestDTO
import com.bel.petproject.data.models.CreationResponseDTO
import com.bel.petproject.data.models.ImageDTO
import com.bel.petproject.models.creationResponse.CreationRequest
import com.bel.petproject.models.creationResponse.CreationResponse
import com.bel.petproject.models.creationResponse.Image

fun CreationRequestDTO.toDomainModel(): CreationRequest {
    return CreationRequest(
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
        images = images.map { it.toDomainModel() },
        expired = expired
    )
}

fun CreationRequest.toDTOModel(): CreationRequestDTO {
    return CreationRequestDTO(
        id = id,
        status = status,
        prompt = prompt,
        negativePrompt = negativePrompt,
        model = model,
        width = width,
        height = height,
        highResolution = highResolution,
        steps = steps,
        seed = seed,
        initialImage = initialImage,
        initialImageMode = initialImageMode,
        initialImageStrength = initialImageStrength,
        createdAt = createdAt,
        updatedAt = updatedAt,
        images = images.map { it.toDTOModel() },
        expired = expired
    )
}

fun CreationResponseDTO.toDomainModel(): CreationResponse {
    return CreationResponse(
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