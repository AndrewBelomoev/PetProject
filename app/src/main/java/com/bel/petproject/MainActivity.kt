package com.bel.petproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bel.petproject.ui.navigation.SetupNavGraph
import com.bel.petproject.ui.theme.PetProjectTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PetProjectTheme {

                navController = rememberNavController()
                SetupNavGraph(navController = navController)

            }
        }
    }
}
