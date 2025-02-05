package com.bel.petproject.ui.screens.createScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bel.petproject.models.imageCard.ImageGenerationParameters

@Composable
fun CreateScreen(navController: NavHostController) {
    var model by remember { mutableStateOf("") }
    var aspectRatio by remember { mutableStateOf("") }
    var highResolution by remember { mutableStateOf(false) }
    var images by remember { mutableStateOf(1L) }
    var steps by remember { mutableStateOf(50L) }
    var prompt by remember { mutableStateOf("") }
    var negativePrompt by remember { mutableStateOf("") }
    var seed by remember { mutableStateOf(0L) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = model,
            onValueChange = { model = it },
            label = { Text("Model") }
        )

        TextField(
            value = aspectRatio,
            onValueChange = { aspectRatio = it },
            label = { Text("Aspect Ratio") }
        )

        Switch(
            checked = highResolution,
            onCheckedChange = { highResolution = it },
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text("High Resolution")

        TextField(
            value = images.toString(),
            onValueChange = { images = it.toLongOrNull() ?: 1L },
            label = { Text("Images") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        TextField(
            value = steps.toString(),
            onValueChange = { steps = it.toLongOrNull() ?: 50L },
            label = { Text("Steps") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        TextField(
            value = prompt,
            onValueChange = { prompt = it },
            label = { Text("Prompt") }
        )

        TextField(
            value = negativePrompt,
            onValueChange = { negativePrompt = it },
            label = { Text("Negative Prompt") }
        )

        TextField(
            value = seed.toString(),
            onValueChange = { seed = it.toLongOrNull() ?: 0L },
            label = { Text("Seed") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(onClick = {
            val parameters = ImageGenerationParameters(
                model = model,
                aspectRatio = aspectRatio,
                highResolution = highResolution,
                images = images,
                steps = steps,
                prompt = prompt,
                negativePrompt = negativePrompt,
                seed = seed
            )
            // Добавьте обработку параметров генерации изображения здесь
        }) {
            Text("Generate Image")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateScreen() {
    CreateScreen(navController = rememberNavController())
}
