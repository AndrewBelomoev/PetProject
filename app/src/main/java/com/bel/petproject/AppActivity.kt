package com.bel.petproject

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bel.petproject.data.service.setAppLocale
import com.bel.petproject.service.LocalizationService
import com.bel.petproject.service.ThemeModeService
import com.bel.petproject.ui.screens.applicationScreen.ApplicationScreen
import com.bel.petproject.ui.theme.PetProjectTheme
import org.koin.android.ext.android.inject

class AppActivity : ComponentActivity() {

    private val themeModeService: ThemeModeService by inject()
    private val localizationService: LocalizationService by inject()

    override fun attachBaseContext(newBase: Context) {
        val locale = localizationService.localization.locale
        super.attachBaseContext(newBase.setAppLocale(locale.language))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeMode = themeModeService.themeMode

            PetProjectTheme(theme = themeMode) {
                ApplicationScreen()
            }
        }
    }

}
