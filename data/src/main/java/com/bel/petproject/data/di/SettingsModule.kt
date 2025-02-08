package com.bel.petproject.data.di

import com.bel.petproject.service.ThemeModeService
import com.bel.petproject.data.service.ThemeModeServiceImpl
import com.bel.petproject.data.service.PreferencesServiceImpl
import com.bel.petproject.service.PreferencesService
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val settingsModule = module {
    singleOf(::ThemeModeServiceImpl) {
        bind<ThemeModeService>()
    }

    singleOf(::PreferencesServiceImpl) {
        bind<PreferencesService>()
    }

}
