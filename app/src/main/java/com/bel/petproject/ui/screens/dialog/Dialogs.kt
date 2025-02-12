package com.bel.petproject.ui.screens.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ShowDeleteConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        title = { Text("Подтверждение удаления") },
        text = { Text("Вы уверены, что хотите удалить это изображение?") },
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                onClick = { onConfirm() }
            ) {
                Text("Удалить")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) { Text("Отмена") }
        },
    )
}

@Composable
fun ShowSaveConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        title = { Text("Подтверждение сохранения") },
        text = { Text("Вы уверены, что хотите сохранить это изображение?") },
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                onClick = { onConfirm() }
            ) {
                Text("Сохранить")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) { Text("Отмена") }
        },
    )
}