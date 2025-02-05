package com.bel.petproject.models.imageCard

data class GeneratedImageDetails(
    val id: Long,
    val status: String? = null,
    val prompt: String? = null,
    val negativePrompt: String? = null,
    val width: Long? = null,
    val height: Long? = null,
    val highResolution: Boolean? = null,
    val seed: Long? = null,
    val steps: Long? = null,
    val model: String? = null,
    val initialImage: Any? = null,
    val initialImageMode: Any? = null,
    val initialImageStrength: Any? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val images: List<Image>? = null,
    val expired: Boolean? = null
)