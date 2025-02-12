package com.bel.petproject.data.repositories

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.provider.MediaStore
import com.bel.petproject.repositories.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class ImageRepositoryImpl(
    private val context: Context
) : ImageRepository {
    override suspend fun saveImage(url: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                // Загружаем изображение по URL
                val bitmap = BitmapFactory.decodeStream(URL(url).openStream())

                val contentResolver = context.contentResolver
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, "image.jpg")
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val uri = contentResolver.insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    contentValues
                )
                uri?.let {
                    contentResolver.openOutputStream(it)?.use { outputStream ->
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    } ?: false
                } ?: false
            } catch (e: Exception) {
                false
            }
        }
    }
}