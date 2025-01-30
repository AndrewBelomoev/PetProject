package com.bel.petproject.data.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.bel.petproject.usecases.CreteNewImagesUseCase
import com.bel.petproject.usecases.GetCreatedImagesByIDUseCase

val useCasesModule = module {
    factoryOf(::CreteNewImagesUseCase)
    factoryOf(::GetCreatedImagesByIDUseCase)
}