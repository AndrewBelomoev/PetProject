package com.bel.petproject.ui.screens.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

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
            Button(onClick = { onConfirm() }) { Text("Удалить") }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) { Text("Отмена") }
        },
    )
}