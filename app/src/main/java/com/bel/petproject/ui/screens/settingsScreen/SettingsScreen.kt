package com.bel.petproject.ui.screens.settingsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bel.petproject.ui.theme.Theme

@Composable
fun SettingsScreen(navController: NavHostController, themeState: MutableState<Theme>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "This is the Settings Screen")
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Light Theme")
            RadioButton(
                selected = themeState.value == Theme.LIGHT,
                onClick = { themeState.value = Theme.LIGHT }
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Dark Theme")
            RadioButton(
                selected = themeState.value == Theme.DARK,
                onClick = { themeState.value = Theme.DARK }
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "System Theme")
            RadioButton(
                selected = themeState.value == Theme.SYSTEM,
                onClick = { themeState.value = Theme.SYSTEM }
            )
        }
    }
}
