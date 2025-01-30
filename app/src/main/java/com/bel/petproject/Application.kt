package com.bel.petproject

import android.app.Application
import com.bel.petproject.data.di.allModulesModule
import com.bel.petproject.di.allViewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(
                allModulesModule,
                allViewModelsModule
            )
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}