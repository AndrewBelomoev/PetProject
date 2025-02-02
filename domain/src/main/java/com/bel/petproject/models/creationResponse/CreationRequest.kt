package com.bel.petproject.models.creationResponse

data class CreationRequest(
    val model: String? = null,
    val aspectRatio: String? = null,
    val highResolution: Boolean? = null,
    val images: Long? = null,
    val steps: Long? = null,
    val prompt: String? = null,
    val negativePrompt: String? = null,
    val seed: Long? = null
)
