package com.globant.myapplication.presentation.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LabelValueRow(label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "$label:",
            fontWeight = FontWeight.Bold,
            modifier =
            Modifier.widthIn(min = 100.dp)
        )
        Text(text = value)
    }
}