package com.globant.myapplication.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globant.myapplication.domain.usecase.ReminderUseCase
import com.globant.myapplication.presentation.mapper.toModel
import com.globant.myapplication.presentation.state.ReminderState
import com.globant.myapplication.presentation.utils.notification.scheduleReminderAlarm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(
    val useCase: ReminderUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ReminderState())
    val state: StateFlow<ReminderState> = _state.asStateFlow()

    init {
        _state.value = ReminderState()
    }

    fun saveReminder(reminder: ReminderState, context: Context){
        _state.value = reminder
        viewModelScope.launch {
            useCase.saveReminderList(reminder = reminder.toModel())
        }
        scheduleReminderAlarm(reminder, context)
    }
}