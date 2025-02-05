package com.bel.petproject.ui.screens.dialog

import android.provider.CalendarContract.Colors
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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