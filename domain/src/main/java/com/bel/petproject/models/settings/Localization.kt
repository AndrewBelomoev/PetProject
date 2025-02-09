package com.bel.petproject.models.settings

import java.util.Locale

enum class Localization(val locale: Locale) {
    ENGLISH(Locale("en")),
    RUSSIAN(Locale("ru"))
}