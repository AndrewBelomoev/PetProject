package com.bel.petproject.usecases.local

import com.bel.petproject.repositories.ImagesLocalRepository

class SaveImageToGalleryUseCase(private val repository: ImagesLocalRepository) {
    suspend operator fun invoke(url: String): Boolean {
        return repository.saveImage(url)
    }
}