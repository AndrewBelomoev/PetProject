package com.bel.petproject.ui.screens.databaseScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.bel.petproject.models.imageCard.GeneratedImageDetails
import com.bel.petproject.models.imageCard.Image

@Composable
fun SavedImageDetailsViewHolder(
    generatedImageDetails: GeneratedImageDetails,
    onCardClick: (GeneratedImageDetails) -> Unit,
    onImageClick: (Image) -> Unit,
    onImageLongClick: (Image) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCardClick(generatedImageDetails) }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {

            Text(
                text = "ID: ${generatedImageDetails.id}",
                Modifier.clickable { },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Prompt: ${generatedImageDetails.prompt}",
                Modifier.clickable { },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            (generatedImageDetails.images ?: listOf(
                Image(
                    id = 1,
                    url = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/1200px-No-Image-Placeholder.svg.png"
                )
            )).forEach { image ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onImageClick(image) }
                ) {
                    val painter = rememberAsyncImagePainter(model = image.url)
                    val painterState by painter.state.collectAsState()

                    Log.d("ImageLoading", "Loading image: ${image.url}, state: $painterState")

                    when (painterState) {
                        is AsyncImagePainter.State.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(40.dp)
                                    .align(Alignment.Center)
                            )
                        }

                        is AsyncImagePainter.State.Error -> {
                            Text(
                                text = "Error loading image",
                                modifier = Modifier.align(Alignment.Center),
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        else -> {
                            Image(
                                painter = painter,
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SavedImageDetailsViewHolderPreview() {
    SavedImageDetailsViewHolder(
        generatedImageDetails = GeneratedImageDetails(
            id = 123456789L,
            status = "Completed",
            prompt = "Example prompt",
            negativePrompt = "Example negative prompt",
            width = 1024L,
            height = 768L,
            highResolution = true,
            seed = 987654321L,
            steps = 100L,
            model = "Example model",
            initialImage = null,
            initialImageMode = null,
            initialImageStrength = null,
            createdAt = "2025-02-01T00:00:00Z",
            updatedAt = "2025-02-01T12:00:00Z",
            images = listOf(
                Image(
                    1,
                    "https://tmp.starryai.com/api/109582/727ef03c-2a69-4381-b5f5-c6d028e046e7.png"
                ),
                Image(
                    2,
                    "https://tmp.starryai.com/api/109582/727ef03c-2a69-4381-b5f5-c6d028e046e7.png"
                )
            ),
            expired = false
        ),
        onCardClick = {},
        onImageClick = {},
        onImageLongClick = {},
    )
}

