package com.bel.petproject.data.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bel.petproject.data.models.GeneratedImageDbEntity

@Database(entities = [GeneratedImageDbEntity::class], version = 1)
@TypeConverters(Converters::class)
internal abstract class GeneratedImageDatabase : RoomDatabase() {
    abstract fun generatedImageDetails(): GeneratedImageDao
}