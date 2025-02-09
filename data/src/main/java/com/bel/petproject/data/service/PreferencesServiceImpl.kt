package com.bel.petproject.data.service

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.bel.petproject.models.settings.Localization
import com.bel.petproject.models.settings.ThemeMode
import com.bel.petproject.service.PreferencesService

class PreferencesServiceImpl(context: Context) : PreferencesService {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("preferences_service", Context.MODE_PRIVATE)

    companion object {
        private const val THEME_MODE_KEY = "theme_mode"
        private const val LOCALIZATION_KEY = "localization"
        private const val API_KEY = "api_key"
    }

    override var themeMode: ThemeMode
        get() {
            val themeModeString = sharedPreferences.getString(THEME_MODE_KEY, ThemeMode.SYSTEM.name)
            return ThemeMode.valueOf(themeModeString ?: ThemeMode.SYSTEM.name)
        }
        set(value) {
            sharedPreferences.edit {
                putString(THEME_MODE_KEY, value.name)
            }
        }

    override var localization: Localization
        get() {
            val languageString =
                sharedPreferences.getString(LOCALIZATION_KEY, Localization.ENGLISH.name)
            return Localization.valueOf(languageString ?: Localization.ENGLISH.name)
        }
        set(value) {
            sharedPreferences.edit {
                putString(LOCALIZATION_KEY, value.name)
            }
        }

    var apiKey: String?
        get() = sharedPreferences.getString(API_KEY, null)
        set(value) {
            sharedPreferences.edit {
                putString(API_KEY, value)
            }
        }

}
