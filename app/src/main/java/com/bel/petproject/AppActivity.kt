package com.bel.petproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.bel.petproject.ui.screens.applicationScreen.ApplicationScreen
import com.bel.petproject.ui.theme.PetProjectTheme
import com.bel.petproject.ui.theme.Theme

class AppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeState = remember { mutableStateOf(Theme.SYSTEM) }
            PetProjectTheme(theme = themeState.value ) {
                ApplicationScreen(themeState = themeState)
            }
        }
    }
}
