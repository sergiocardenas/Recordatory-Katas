package com.globant.myapplication.presentation.state

import java.util.*

data class ReminderState(
    val title: String = "New title",
    val description: String = "New description",
    val date: Long = Date().time,
    val type: Urgency = Urgency.NORMAL
)

enum class Urgency{
    URGENT,
    NORMAL
}