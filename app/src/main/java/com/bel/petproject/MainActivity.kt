package com.bel.petproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bel.petproject.ui.screens.mainScreen.MainScreen
import com.bel.petproject.ui.theme.PetProjectTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PetProjectTheme {
                MainScreen()
//                ListScreen()
            }
        }
    }
}
