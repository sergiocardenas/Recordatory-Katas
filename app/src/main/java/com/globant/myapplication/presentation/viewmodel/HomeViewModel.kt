package com.globant.myapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.globant.myapplication.presentation.state.ReminderListState
import com.globant.myapplication.presentation.state.ReminderState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    private val _state = MutableStateFlow(ReminderListState())
    val state: StateFlow<ReminderListState> = _state.asStateFlow()

    init {
        _state.value = ReminderListState(
            reminderList = listOf(ReminderState())
        )
    }
}