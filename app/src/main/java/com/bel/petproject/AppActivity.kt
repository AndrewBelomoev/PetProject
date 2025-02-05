package com.bel.petproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bel.petproject.ui.screens.applicationScreen.ApplicationScreen
import com.bel.petproject.ui.theme.PetProjectTheme

class AppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PetProjectTheme {
                ApplicationScreen()
            }
        }
    }
}
