package com.globant.myapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.globant.myapplication.presentation.state.ReminderState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateViewModel : ViewModel() {
    private val _state = MutableStateFlow(ReminderState())
    val state: StateFlow<ReminderState> = _state.asStateFlow()


    init {
        _state.value = ReminderState()
    }

    fun saveReminder(reminder: ReminderState){
        _state.value = reminder
    }
}