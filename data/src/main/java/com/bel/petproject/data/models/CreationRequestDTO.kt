package com.bel.petproject.data.models

data class CreationRequestDTO(
    val id: Long,
    val status: String,
    val prompt: String,
    val negativePrompt: String,
    val model: String,
    val width: Int,
    val height: Int,
    val highResolution: Boolean,
    val steps: Int,
    val seed: Long,
    val initialImage: Any,
    val initialImageMode: Any,
    val initialImageStrength: Any,
    val createdAt: String,
    val updatedAt: String,
    val images: List<ImageDTO>,
    val expired: Boolean
)
