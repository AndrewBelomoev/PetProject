package com.bel.petproject.data.database.room

import androidx.room.TypeConverter
import com.bel.petproject.data.models.ImageDbEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromAny(value: Any?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toAny(value: String?): Any? {
        return Gson().fromJson(value, Any::class.java)
    }

    @TypeConverter
    fun fromImageDbEntityList(images: List<ImageDbEntity>?): String? {
        return Gson().toJson(images)
    }

    @TypeConverter
    fun toImageDbEntityList(images: String?): List<ImageDbEntity>? {
        val listType = object : TypeToken<List<ImageDbEntity>>() {}.type
        return Gson().fromJson(images, listType)
    }
}