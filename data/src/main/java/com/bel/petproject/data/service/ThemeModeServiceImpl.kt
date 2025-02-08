package com.bel.petproject.data.service

import androidx.compose.runtime.mutableStateOf
import com.bel.petproject.models.settings.ThemeMode
import com.bel.petproject.service.PreferencesService
import com.bel.petproject.service.ThemeModeService

class ThemeModeServiceImpl(private val preferencesService: PreferencesService) : ThemeModeService {
    private val _themeMode = mutableStateOf(preferencesService.themeMode)

    override var themeMode: ThemeMode
        get() = _themeMode.value
        set(value) {
            _themeMode.value = value
            preferencesService.themeMode = value
        }
}