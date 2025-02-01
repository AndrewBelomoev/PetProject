package com.bel.petproject.data.models

data class CreationRequestDTO(
    val model: String,
    val aspectRatio: String,
    val highResolution: Boolean,
    val images: Long,
    val steps: Long,
    val prompt: String,
    val negativePrompt: String,
    val seed: Long,
)
