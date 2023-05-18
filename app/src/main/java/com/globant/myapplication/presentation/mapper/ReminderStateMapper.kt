package com.globant.myapplication.presentation.mapper

import com.globant.myapplication.domain.model.ReminderModel
import com.globant.myapplication.presentation.state.ReminderState
import com.globant.myapplication.presentation.state.Urgency

fun ReminderModel.toState() = ReminderState(
    id = id,
    title = title,
    description = description,
    date = date,
    type = if (Urgency.URGENT.name == type) Urgency.URGENT else Urgency.NORMAL
)

fun ReminderState.toModel() = ReminderModel(
    id = id,
    title = title,
    description = description,
    date = date,
    type = type.name
)