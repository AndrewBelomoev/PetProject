package com.bel.petproject.usecases.local

import com.bel.petproject.repositories.ImageRepository

class SaveImageToGalleryUseCase(private val repository: ImageRepository) {
    suspend operator fun invoke(url: String): Boolean {
        return repository.saveImage(url)
    }
}