package com.bel.petproject.data.di

import com.bel.petproject.data.repositories.ImagesRemoteReposImpl
import com.bel.petproject.repositories.ImagesRemoteRepository
import com.bel.petproject.data.repositories.ImagesLocalReposImpl
import com.bel.petproject.repositories.ImagesLocalRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val repositoryModule = module {

    singleOf(::ImagesRemoteReposImpl) {
        bind<ImagesRemoteRepository>()
    }

    singleOf(::ImagesLocalReposImpl) {
        bind<ImagesLocalRepository>()
    }
}