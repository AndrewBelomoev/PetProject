package com.bel.petproject.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.bel.petproject.models.imageCard.GeneratedImageDetails
import com.bel.petproject.models.imageCard.Image

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCardViewHolder(
    mode: Int,
    generatedImageDetails: GeneratedImageDetails,
    onCardClick: (GeneratedImageDetails) -> Unit,
    onImageClick: (Image) -> Unit,
    onImageLongClick: (Image) -> Unit,
    onDetailsButtonClick: (GeneratedImageDetails) -> Unit,
    onSaveButtonClick: (GeneratedImageDetails) -> Unit,
    onDelImageButtonClick: (Image) -> Unit,
    onDelCardButtonClick: (GeneratedImageDetails) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onCardClick(generatedImageDetails) }
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {

            if (mode == 1) {
                Text(
                    text = "Prompt: ${generatedImageDetails.prompt}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Negative Prompt: ${generatedImageDetails.negativePrompt}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Status: ${generatedImageDetails.status}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            onDetailsButtonClick(generatedImageDetails)
                        },
                    ) {
                        Text(text = "Details")
                    }
                    Button(
                        onClick = { onSaveButtonClick(generatedImageDetails) },
                    ) {
                        Icon(imageVector = Icons.Default.AddCircle, contentDescription = "")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Save to database")
                    }
                }

                LazyColumn {
                    items(
                        generatedImageDetails.images ?: listOf(Image(id = 1, url = ""))
                    ) { image ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clip(RoundedCornerShape(8.dp))
                        ) {
                            val painter = rememberAsyncImagePainter(model = image.url)
                            val painterState by painter.state.collectAsState()

                            if (painterState is AsyncImagePainter.State.Loading) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .align(Alignment.Center)
                                )
                            } else {
                                Image(
                                    painter = painter,
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .combinedClickable(
                                            onClick = { onImageClick(image) },
                                            onLongClick = { onImageLongClick(image) }
                                        )
                                )

                                IconButton(
                                    onClick = { onDelImageButtonClick(image) },
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .zIndex(1f)
                                        .padding(8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Delete,
                                        contentDescription = "deleteButton"
                                    )
                                }
                            }
                        }
                    }
                }
            }
            if (mode == 2) {
                Row {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Prompt: ${generatedImageDetails.prompt}",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    IconButton(onClick = { onDelCardButtonClick(generatedImageDetails) }) {
                        Icon(imageVector = Icons.Filled.Delete, contentDescription = "")
                    }

                }

                (generatedImageDetails.images ?: listOf()).forEach { image ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { onImageClick(image) }
                    ) {
                        val painter = rememberAsyncImagePainter(model = image.url)
                        val painterState by painter.state.collectAsState()

                        if (painterState is AsyncImagePainter.State.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(40.dp)
                                    .align(Alignment.Center)
                            )
                        } else {
                            Image(
                                painter = painter,
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .combinedClickable(
                                        onClick = { onImageClick(image) },
                                        onLongClick = { onImageLongClick(image) }
                                    )
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
private fun ImageCardViewHolderPreview() {
    ImageCardViewHolder(
        generatedImageDetails = GeneratedImageDetails(
            id = 123456789L,
            status = "Completed",
            prompt = "Example prompt Example prompt Example prompt Example prompt",
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
        mode = 2,
        onCardClick = {},
        onImageClick = {},
        onImageLongClick = {},
        onDelCardButtonClick = {},
        onDetailsButtonClick = {},
        onSaveButtonClick = {},
        onDelImageButtonClick = {}
    )
}
