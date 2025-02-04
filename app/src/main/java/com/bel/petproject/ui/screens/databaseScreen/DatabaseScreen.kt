package com.bel.petproject.ui.screens.databaseScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewModelScope
import com.bel.petproject.models.creationResponse.GeneratedImageDetails
import com.bel.petproject.models.creationResponse.Image
import org.koin.androidx.compose.koinViewModel
import java.lang.reflect.Modifier

@Composable
fun DatabaseScreen() {
    val viewModel: DbViewModel = koinViewModel()

    val images by viewModel.images.collectAsState()

    val newImageDetails = GeneratedImageDetails(
        id = 123L,
        status = "Completed",
        prompt = "Example prompt 2",
        negativePrompt = "Example negative prompt",
        width = 1024L,
        height = 768L,
        highResolution = true,
        seed = 987654321L,
        steps = 10L,
        model = "Example model",
        initialImage = null,
        initialImageMode = null,
        initialImageStrength = null,
        createdAt = "2025-02-01T00:00:00Z",
        updatedAt = "2025-02-01T12:00:00Z",
        images = listOf(
            Image(
                1,
                "https://www.bkacontent.com/wp-content/uploads/2016/06/Depositphotos_31146757_l-2015.jpg"
            ),
            Image(
                2,
                "https://plus.unsplash.com/premium_photo-1674331206157-f3e1581d376b?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8Z29kfGVufDB8fDB8fHww"
            )
        ),
        expired = false
    )
    val context = LocalContext.current

    Column {
        Button(onClick = {
            viewModel.save(newImageDetails)
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Save")
        }
        LazyColumn {
            items(images) {
                Text(text = it.id.toString() ?: "")
                Text(text = it.prompt ?: "")
                Text(text = it.prompt ?: "")
                it.images?.forEach { image ->
                    Text(text = image.url ?: "Nu url")
                }
            }
        }
    }

}

@Preview
@Composable
fun DatabaseScreenPreview() {
    DatabaseScreen()
}