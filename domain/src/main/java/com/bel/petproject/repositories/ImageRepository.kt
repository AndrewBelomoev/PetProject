package com.bel.petproject.repositories

interface ImageRepository {
    suspend fun saveImage(url: String): Boolean
}