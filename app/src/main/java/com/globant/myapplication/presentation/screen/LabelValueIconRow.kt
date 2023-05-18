package com.globant.myapplication.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.globant.myapplication.presentation.state.Urgency
import com.globant.myreminders.R

@Composable
fun LabelValueIconRow(label: String, value: String) {
    val urgencyIC = if(value == Urgency.URGENT.toString()){
        R.drawable.ic_exclamation_urgent
    }else{
        R.drawable.ic_exclamation
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "$label:",
            fontWeight = FontWeight.Bold,
            modifier =
            Modifier.widthIn(min = 100.dp)
        )
        Image(
            painter = painterResource(id = urgencyIC),
            contentDescription = "icon_type",
            modifier = Modifier
                .size(25.dp)
                .padding(end = 5.dp, top = 5.dp, bottom = 5.dp)
        )
        Text(text = value)
    }
}