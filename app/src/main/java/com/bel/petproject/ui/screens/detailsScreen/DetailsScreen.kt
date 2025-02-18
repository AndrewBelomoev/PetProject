package com.bel.petproject.ui.screens.detailsScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bel.petproject.R
import com.bel.petproject.ui.screens.SharedViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(navController: NavHostController) {
    val sharedViewModel: SharedViewModel = koinViewModel()
    val sharedData by sharedViewModel.imageDetails.collectAsState()
    Log.d("DetailsScreenSharedData", "your data: $sharedData")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
                tint = Color.White
            )
        }

        sharedData?.let { data ->
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = stringResource(id = R.string.id, data.id))
                Text(text = stringResource(id = R.string.status, data.status ?: ""))
                Text(text = stringResource(id = R.string.prompt, data.prompt ?: ""))
                Text(
                    text = stringResource(
                        id = R.string.negative_prompt,
                        data.negativePrompt ?: ""
                    )
                )
                Text(text = stringResource(id = R.string.width, data.width ?: ""))
                Text(text = stringResource(id = R.string.height, data.height ?: ""))
                Text(
                    text = stringResource(
                        id = R.string.high_resolution,
                        data.highResolution ?: ""
                    )
                )
                Text(text = stringResource(id = R.string.seed, data.seed ?: ""))
                Text(text = stringResource(id = R.string.steps, data.steps ?: ""))
                Text(text = stringResource(id = R.string.model, data.model ?: ""))
            }
        } ?: run {
            Text(text = stringResource(id = R.string.no_data_available))
        }
    }
}
