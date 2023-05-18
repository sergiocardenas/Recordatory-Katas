package com.globant.myapplication.presentation.state

import java.util.*

data class ReminderState(
    val id: Int = -1,
    val title: String = "New title",
    val description: String = "New description",
    val date: Long = Calendar.getInstance().timeInMillis,
    val type: Urgency = Urgency.NORMAL
)

enum class Urgency{
    URGENT,
    NORMAL
}