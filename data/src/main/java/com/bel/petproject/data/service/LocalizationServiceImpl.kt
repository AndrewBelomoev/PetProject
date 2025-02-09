package com.bel.petproject.data.service

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.bel.petproject.models.settings.Localization
import com.bel.petproject.service.LocalizationService
import com.bel.petproject.service.PreferencesService

class LocalizationServiceImpl(
    private val context: Context,
    private val preferencesService: PreferencesService
) : LocalizationService {
    private val _localization = mutableStateOf(preferencesService.localization)

    override var localization: Localization
        get() = _localization.value
        set(value) {
            _localization.value = value
            preferencesService.localization = value

        }

}
