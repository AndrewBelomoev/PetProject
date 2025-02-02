package com.bel.petproject.data.database.room

import androidx.room.RoomDatabase

internal abstract class GeneratedImageDetailsDatabase : RoomDatabase() {
    abstract fun creationResponseDao(): GeneratedImageDetailsDao
}