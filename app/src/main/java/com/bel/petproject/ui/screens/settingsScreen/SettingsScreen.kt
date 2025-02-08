package com.bel.petproject.ui.screens.settingsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bel.petproject.models.settings.ThemeMode
import com.bel.petproject.service.ThemeModeService
import org.koin.androidx.compose.getKoin

@Composable
fun SettingsScreen(navController: NavHostController) {
    val themeModeService: ThemeModeService = getKoin().get()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Экран Настроек")
        Spacer(modifier = Modifier.height(16.dp))

        ThemeOption(
            text = "Светлая Тема",
            selected = themeModeService.themeMode == ThemeMode.LIGHT,
            onClick = { themeModeService.themeMode = ThemeMode.LIGHT }
        )

        ThemeOption(
            text = "Темная Тема",
            selected = themeModeService.themeMode == ThemeMode.DARK,
            onClick = { themeModeService.themeMode = ThemeMode.DARK }
        )

        ThemeOption(
            text = "Системная Тема",
            selected = themeModeService.themeMode == ThemeMode.SYSTEM,
            onClick = { themeModeService.themeMode = ThemeMode.SYSTEM }
        )
    }
}

@Composable
fun ThemeOption(text: String, selected: Boolean, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = text)
        RadioButton(
            selected = selected,
            onClick = onClick
        )
    }
}
