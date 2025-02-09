package com.bel.petproject.data.service

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

fun Context.setAppLocale(language: String): Context {
    val locale = Locale(language)
    Locale.setDefault(locale)

    val configuration = Configuration(resources.configuration)
    configuration.setLocale(locale)

    return createConfigurationContext(configuration)
}