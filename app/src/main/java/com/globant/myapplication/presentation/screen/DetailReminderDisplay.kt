package com.globant.myapplication.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.globant.myapplication.presentation.state.ReminderState
import com.globant.myapplication.presentation.utils.getDateFormat
import java.util.*

@Composable
fun DetailReminderDisplay(
    detailState: State<ReminderState>,
) {
    LabelValueRow(label = "Name", value = detailState.value.title)
    LabelValueRow(label = "Description", value = detailState.value.description)
    LabelValueRow(label = "Date", value = getDateFormat().format(Date(detailState.value.date)))
    LabelValueIconRow(label = "Type", value = detailState.value.type.toString())
}