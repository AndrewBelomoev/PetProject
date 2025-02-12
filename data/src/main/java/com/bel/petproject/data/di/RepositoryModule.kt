package com.bel.petproject.data.di

import com.bel.petproject.data.repositories.ImagesRemoteReposImpl
import com.bel.petproject.repositories.ImagesRemoteRepository
import com.bel.petproject.data.repositories.ImagesLocalReposImpl
import com.bel.petproject.repositories.ImagesLocalRepository
import com.bel.petproject.data.repositories.ImageRepositoryImpl
import com.bel.petproject.repositories.ImageRepository
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

    singleOf(::ImageRepositoryImpl) {
        bind<ImageRepository>()
    }

}