package com.bel.petproject.ui.screens.settingsScreen

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bel.petproject.R
import com.bel.petproject.data.service.PreferencesServiceImpl
import com.bel.petproject.models.settings.Localization
import com.bel.petproject.models.settings.ThemeMode
import com.bel.petproject.service.LocalizationService
import com.bel.petproject.service.ThemeModeService
import org.koin.androidx.compose.getKoin

@Composable
fun SettingsScreen() {
    val themeModeService: ThemeModeService = getKoin().get()
    val localizationService: LocalizationService = getKoin().get()
    val preferencesService: PreferencesServiceImpl = getKoin().get()
    val context = LocalContext.current
    var apiKey by remember { mutableStateOf(preferencesService.apiKey ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(R.string.settings_screen),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.theme_option),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                ThemeOption(
                    text = stringResource(R.string.theme_light),
                    selected = themeModeService.themeMode == ThemeMode.LIGHT,
                    onClick = { themeModeService.themeMode = ThemeMode.LIGHT }
                )
                ThemeOption(
                    text = stringResource(R.string.theme_dark),
                    selected = themeModeService.themeMode == ThemeMode.DARK,
                    onClick = { themeModeService.themeMode = ThemeMode.DARK }
                )
                ThemeOption(
                    text = stringResource(R.string.theme_system),
                    selected = themeModeService.themeMode == ThemeMode.SYSTEM,
                    onClick = { themeModeService.themeMode = ThemeMode.SYSTEM }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.language_option),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row {
                    LanguageOption(
                        text = stringResource(R.string.language_english),
                        selected = localizationService.localization == Localization.ENGLISH,
                        onClick = {
                            localizationService.localization = Localization.ENGLISH
                            (context as? Activity)?.recreate()
                        }
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    LanguageOption(
                        text = stringResource(R.string.language_russian),
                        selected = localizationService.localization == Localization.RUSSIAN,
                        onClick = {
                            localizationService.localization = Localization.RUSSIAN
                            (context as? Activity)?.recreate()
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.api_key_option),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                TextField(
                    value = apiKey,
                    onValueChange = { apiKey = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                Button(onClick = { preferencesService.apiKey = apiKey }) {
                    Text(text = stringResource(R.string.save_api_key_button))
                }
            }
        }
    }
}

@Composable
private fun ThemeOption(text: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
        RadioButton(
            selected = selected,
            onClick = onClick
        )
    }
}

@Composable
private fun LanguageOption(text: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
        RadioButton(
            selected = selected,
            onClick = onClick
        )
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen()
}

