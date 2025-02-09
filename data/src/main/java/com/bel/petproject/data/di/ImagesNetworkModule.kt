package com.bel.petproject.data.di

import com.bel.petproject.data.api.starryAiAPI.AuthInterceptor
import com.bel.petproject.data.api.starryAiAPI.StarryAiAPI
import com.bel.petproject.data.service.PreferencesServiceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

internal val networkModule = module {

    single {
        OkHttpClient
            .Builder()
            .build()
    }



    single(qualifier = qualifier("starry_ai_api")) {

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val preferencesService: PreferencesServiceImpl = get()

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(AuthInterceptor(preferencesService.apiKey ?: ""))
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.starryai.com/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>(qualifier("starry_ai_api")).create<StarryAiAPI>()
    }

}


