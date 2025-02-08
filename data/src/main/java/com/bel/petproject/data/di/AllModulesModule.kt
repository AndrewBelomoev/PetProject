package com.bel.petproject.data.di

import org.koin.dsl.module

val allModulesModule = module {
    includes(
        networkModule,
        roomDatabaseModule,
        repositoryModule,
        useCasesModule,
        settingsModule
    )
}