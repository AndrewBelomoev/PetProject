package com.bel.petproject.data.di

import com.bel.petproject.usecases.local.DeleteGeneratedImageFromDatabaseUseCase
import com.bel.petproject.usecases.local.GetAllGeneratedImagesFromDatabaseUseCase
import com.bel.petproject.usecases.local.SaveGeneratedImageToDatabaseUseCase
import com.bel.petproject.usecases.local.SaveImageToGalleryUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.bel.petproject.usecases.remote.CreteNewImagesUseCase
import com.bel.petproject.usecases.remote.GetCreatedImagesByIDUseCase

val useCasesModule = module {
    factoryOf(::CreteNewImagesUseCase)
    factoryOf(::GetCreatedImagesByIDUseCase)
    factoryOf(::DeleteGeneratedImageFromDatabaseUseCase)
    factoryOf(::GetAllGeneratedImagesFromDatabaseUseCase)
    factoryOf(::SaveGeneratedImageToDatabaseUseCase)
    factoryOf(::SaveImageToGalleryUseCase)

}