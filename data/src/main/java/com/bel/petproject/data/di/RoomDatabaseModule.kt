package com.bel.petproject.data.di

import androidx.room.Room
import com.bel.petproject.data.database.room.GeneratedImageDatabase
import org.koin.dsl.module

internal val roomDatabaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            GeneratedImageDatabase::class.java,
            name = "GeneratedImagesDatabase_1"
        )
            .build()
    }

    single {
        get<GeneratedImageDatabase>().generatedImageDetails()
    }

}