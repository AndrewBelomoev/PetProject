package com.bel.petproject.data.models

data class CreationRequestDTO(
    val id: Long,
    val status: String,
    val prompt: String,
    val negativePrompt: String,
    val width: Long,
    val height: Long,
    val highResolution: Boolean,
    val seed: Long,
    val steps: Long,
    val model: String,
    val initialImage: Any?,
    val initialImageMode: Any?,
    val initialImageStrength: Any?,
    val createdAt: String,
    val updatedAt: String,
    val images: List<ImageDTO>,
    val expired: Boolean,
)
