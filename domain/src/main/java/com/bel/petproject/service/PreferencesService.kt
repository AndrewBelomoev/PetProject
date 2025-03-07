package com.bel.petproject.service

import com.bel.petproject.models.settings.Localization
import com.bel.petproject.models.settings.ThemeMode

interface PreferencesService {
    var themeMode: ThemeMode
    var localization: Localization
}