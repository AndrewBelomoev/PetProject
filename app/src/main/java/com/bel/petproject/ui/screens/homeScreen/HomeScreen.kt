package com.bel.petproject.ui.screens.homeScreen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = koinViewModel()
    val context = LocalContext.current



    CreationCardViewHolder(
        creation = viewModel.creation,
        onCardClick = {
            Toast.makeText(context, "Card clicked: $it", Toast.LENGTH_SHORT).show()
        },
        onImageClick = {
            Toast.makeText(context, "Image clicked: $it", Toast.LENGTH_SHORT).show()
        },
        onImageLongClick = {

        })

}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}