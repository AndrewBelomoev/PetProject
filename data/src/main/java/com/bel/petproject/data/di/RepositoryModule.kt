package com.bel.petproject.data.di

import com.bel.petproject.data.repositories.ImagesRemoteReposImpl
import com.bel.petproject.repositories.ImagesRemoteRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val repositoryModule = module {

    singleOf(::ImagesRemoteReposImpl) {
        bind<ImagesRemoteRepository>()
    }

}