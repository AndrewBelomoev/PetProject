package com.bel.petproject.models.creationResponse

data class CreationRequest(
    val model: String,
    val aspectRatio: String,
    val highResolution: Boolean,
    val images: Long,
    val steps: Long,
    val prompt: String,
    val negativePrompt: String,
    val seed: Long,
)
