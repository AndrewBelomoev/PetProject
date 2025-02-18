package com.bel.petproject.ui.screens.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.bel.petproject.R

@Composable
fun ShowDeleteConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        title = { Text(stringResource(R.string.dialog_delete_confirmation_title)) },
        text = { Text(stringResource(R.string.dialog_delete_confirmation_text)) },
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                onClick = { onConfirm() }
            ) {
                Text(stringResource(R.string.dialog_button_delete))
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) { Text(stringResource(R.string.dialog_button_cancel)) }
        },
    )
}

@Composable
fun ShowSaveConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        title = { Text(stringResource(R.string.dialog_save_confirmation_title)) },
        text = { Text(stringResource(R.string.dialog_save_confirmation_text)) },
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                onClick = { onConfirm() }
            ) {
                Text(stringResource(R.string.dialog_button_save))
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) { Text(stringResource(R.string.dialog_button_cancel)) }
        },
    )
}
