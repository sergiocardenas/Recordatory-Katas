package com.globant.myapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.globant.myapplication.presentation.state.ReminderState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel : ViewModel() {
    private val _state = MutableStateFlow(ReminderState())
    val state: StateFlow<ReminderState> = _state.asStateFlow()

    private val _editMode = MutableStateFlow(false)
    val editMode: StateFlow<Boolean> = _editMode.asStateFlow()

    init {
        _state.value = ReminderState()
    }

    fun changeEditMode(){
        _editMode.value = !_editMode.value
    }

    fun updateReminder(reminder: ReminderState){
        _state.value = reminder
    }
}