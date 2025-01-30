package com.bel.petproject.ui.screens.detailsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel


@Composable
fun DetailsScreen(navController: NavController) {

    val viewModel: DetailsViewModel = koinViewModel()


    viewModel.fetchImagesByID(109150)


    val creationResponse by viewModel.creationResponse.collectAsState()
    val imageUrls = creationResponse?.images?.map { it.url } ?: listOf("Empty")

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center
    ) {
        imageUrls.forEach { url ->
            Text(
                text = url,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}

@Preview
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(
        navController = rememberNavController()
    )
}
