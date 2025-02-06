package com.bel.petproject.ui.screens.createScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bel.petproject.models.imageCard.AspectRatio
import com.bel.petproject.models.imageCard.ImageGenerationParameters
import com.bel.petproject.models.imageCard.ImageModel
import com.bel.petproject.ui.navigation.Screen
import com.bel.petproject.ui.screens.SharedViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateScreen(navController: NavHostController) {

    val sharedViewModel: SharedViewModel = koinViewModel()

    val scrollState = rememberScrollState()
    var model by rememberSaveable { mutableStateOf<String?>("photography") }
    var aspectRatio by rememberSaveable { mutableStateOf<String?>("square") }
    var highResolution by rememberSaveable { mutableStateOf(false) }
    var images by rememberSaveable { mutableLongStateOf(1L) }
    var steps by rememberSaveable { mutableLongStateOf(20L) }
    var prompt by rememberSaveable { mutableStateOf("") }
    var negativePrompt by rememberSaveable { mutableStateOf("") }
    var seed by rememberSaveable { mutableStateOf<Long?>(null) }

    Column(modifier = Modifier.verticalScroll(scrollState)) {
        PromptInputField(prompt) { prompt = it }
        NegativePromptInputField(negativePrompt) { negativePrompt = it }
        ModelSelection(model) { model = it }
        ImageQuantity(images) { images = it }
        AspectRatioSelection(aspectRatio) { aspectRatio = it }
        HighResolutionSelection(highResolution) { highResolution = it }
        SeedInputField(seed) { seed = it }
        StepsInputField(steps) { steps = it }
        GenerateImageButton(
            model = model,
            aspectRatio = aspectRatio,
            highResolution = highResolution,
            images = images,
            steps = steps,
            prompt = prompt,
            negativePrompt = negativePrompt,
            seed = seed,
            sharedViewModel,
            navController
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewCreateScreen() {
    CreateScreen(navController = rememberNavController())
}

@Composable
fun GenerateImageButton(
    model: String?,
    aspectRatio: String?,
    highResolution: Boolean?,
    images: Long?,
    steps: Long?,
    prompt: String?,
    negativePrompt: String?,
    seed: Long?,
    sharedViewModel: SharedViewModel,
    navController: NavHostController
) {
    Button(
        onClick = {
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
            sharedViewModel.setGenerationParameters(parameters)
            navController.navigate(Screen.Home.route)

        },
        modifier = Modifier
            .padding(22.dp)
    ) {
        Text(text = "Сгенерировать изображение")
    }
}

@Composable
fun PromptInputField(prompt: String, onPromptChange: (String) -> Unit) {
    var error by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Введите текстовый запрос (prompt):")
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = prompt,
                onValueChange = {
                    onPromptChange(it)
                    error = it.length !in 3..765
                },
                label = { Text("Prompt") },
                modifier = Modifier.fillMaxWidth(),
                isError = error,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
            )
            if (error) {
                Text(
                    text = "Длина текста должна быть от 3 до 765 символов.",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
fun NegativePromptInputField(negativePrompt: String, onNegativePromptChange: (String) -> Unit) {
    var error by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Введите негативный текстовый запрос (negative prompt):")
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = negativePrompt,
                onValueChange = {
                    onNegativePromptChange(it)
                    error = it.length > 765
                },
                label = { Text("Negative Prompt") },
                modifier = Modifier.fillMaxWidth(),
                isError = error,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
            )
            if (error) {
                Text(
                    text = "Длина текста должна быть не более 765 символов.",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
fun ModelSelection(model: String?, onModelChange: (String?) -> Unit) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedModel by rememberSaveable { mutableStateOf(model) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Выберите модель для генерации:")
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
                    .padding(8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedModel ?: "Выберите модель",
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Показать меню")
                    }
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    ImageModel.entries.forEach { model ->
                        DropdownMenuItem(
                            text = { Text(text = model.displayName) },
                            onClick = {
                                selectedModel = model.displayName
                                onModelChange(model.displayName)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ImageQuantity(images: Long, onImagesChange: (Long) -> Unit) {
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Количество изображений:")
            IconButton(onClick = { onImagesChange(maxOf(1, images - 1)) }) {
                Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "Уменьшить")
            }
            Text(text = images.toString())
            IconButton(onClick = { onImagesChange(minOf(12, images + 1)) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Увеличить")
            }
        }
    }
}

@Composable
fun AspectRatioSelection(aspectRatio: String?, onAspectRatioChange: (String?) -> Unit) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedAspectRatio by rememberSaveable { mutableStateOf(aspectRatio) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Выберите соотношение сторон:")
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
                    .padding(8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedAspectRatio ?: "Выберите соотношение сторон",
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Показать меню")
                    }
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    AspectRatio.entries.forEach { ratio ->
                        DropdownMenuItem(
                            text = { Text(text = ratio.displayName) },
                            onClick = {
                                selectedAspectRatio = ratio.displayName
                                onAspectRatioChange(ratio.displayName)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HighResolutionSelection(highResolution: Boolean, onHighResolutionChange: (Boolean) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Высокое разрешение:")
            Spacer(modifier = Modifier.width(16.dp))
            Switch(
                checked = highResolution,
                onCheckedChange = { onHighResolutionChange(it) }
            )
        }
    }
}

@Composable
fun SeedInputField(seed: Long?, onSeedChange: (Long?) -> Unit) {
    var seedText by rememberSaveable { mutableStateOf(seed?.toString() ?: "") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Seed:")
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = seedText,
                onValueChange = {
                    seedText = it
                    onSeedChange(it.toLongOrNull())
                },
                label = { Text("Seed") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
        }
    }
}

@Composable
fun StepsInputField(steps: Long, onStepsChange: (Long) -> Unit) {
    var stepsText by rememberSaveable { mutableStateOf(steps.toString()) }
    var error by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Введите количество итераций (steps):")
            Text(text = "от 1 до 150. По умолчанию значение равно 20.")
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = stepsText,
                onValueChange = {
                    stepsText = it
                    val stepValue = it.toLongOrNull()
                    error = stepValue == null || stepValue !in 1..150
                    onStepsChange(if (error) 20L else stepValue!!)
                },
                label = { Text("Steps") },
                modifier = Modifier.fillMaxWidth(),
                isError = error,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            if (error) {
                Text(
                    text = "Значение должно быть от 1 до 150. Установлено значение по умолчанию: 20.",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}