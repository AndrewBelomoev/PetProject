package com.bel.petproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bel.petproject.service.ThemeModeService
import com.bel.petproject.ui.screens.applicationScreen.ApplicationScreen
import com.bel.petproject.ui.theme.PetProjectTheme
import org.koin.android.ext.android.inject

class AppActivity : ComponentActivity() {

    private val themeModeService: ThemeModeService by inject()

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
