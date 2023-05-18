package com.globant.myapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globant.myapplication.domain.usecase.ReminderUseCase
import com.globant.myapplication.presentation.mapper.toState
import com.globant.myapplication.presentation.state.ReminderListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(
    private val useCase: ReminderUseCase
): ViewModel() {
    private val _state = MutableStateFlow(ReminderListState())
    private val _firstFetch = MutableStateFlow(true)
    val state: StateFlow<ReminderListState> = _state.asStateFlow()

    init {
    }

    fun getReminderList(){
        viewModelScope.launch {
            if(_firstFetch.value){
                _firstFetch.value = false
                useCase.getReminderList().collect(){ reminderList ->
                    if(reminderList.isNotEmpty()){
                        _state.value = ReminderListState(
                            reminderList = reminderList.map { model -> model.toState() }
                        )
                    }
                }
            }else{
                useCase.updateReminderList(_state.value.reminderList.size).collect(){ reminderList ->
                    if(reminderList.isNotEmpty()){
                        _state.value = ReminderListState(
                            reminderList = reminderList.map { model -> model.toState() }
                        )
                    }
                }
            }
        }
    }

}