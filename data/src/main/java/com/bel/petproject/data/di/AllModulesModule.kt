package com.bel.petproject.data.di

import org.koin.dsl.module

val allModulesModule = module {
    includes(
        networkModule,
        repositoryModule,
        useCasesModule
    )
}