package com.bel.petproject.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bel.petproject.data.models.mapper.ImageDbEntity

@Entity
data class GeneratedImageDetailsDbEntity(
    @PrimaryKey(autoGenerate = true)
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
    val initialImage: Any?,
    val initialImageMode: Any?,
    val initialImageStrength: Any?,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val images: List<ImageDbEntity>,
    val expired: Boolean? = null
)
