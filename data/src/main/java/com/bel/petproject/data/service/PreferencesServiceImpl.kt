package com.bel.petproject.data.service

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.bel.petproject.models.settings.ThemeMode
import com.bel.petproject.service.PreferencesService

class PreferencesServiceImpl(context: Context) : PreferencesService {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("preferences_service", Context.MODE_PRIVATE)

    companion object {
        private const val THEME_MODE_KEY = "theme_mode"
        private const val LANGUAGE_KEY = "language"
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

//    override var language: Language
//        get() {
//            val languageString = sharedPreferences.getString(LANGUAGE_KEY, Language.ENGLISH.name)
//            return Language.valueOf(languageString ?: Language.ENGLISH.name)
//        }
//        set(value) {
//            sharedPreferences.edit {
//                putString(LANGUAGE_KEY, value.name)
//            }
//        }

}
