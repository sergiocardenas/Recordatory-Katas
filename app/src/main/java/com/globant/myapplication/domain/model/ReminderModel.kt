package com.globant.myapplication.domain.model

data class ReminderModel(
    val id: Int = -1,
    val title: String = "",
    val description: String = "",
    val date: Long = 0L,
    val type: String = ""
)
