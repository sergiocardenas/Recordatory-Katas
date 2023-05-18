package com.globant.myapplication.domain.usecase

import com.globant.myapplication.domain.model.ReminderModel
import kotlinx.coroutines.flow.Flow

interface ReminderUseCase {
    suspend fun getReminderList(): Flow<List<ReminderModel>>
    suspend fun getReminder(id: Int): Flow<ReminderModel>
    suspend fun updateReminderList(count: Int): Flow<List<ReminderModel>>
    suspend fun saveReminderList(reminder: ReminderModel)
}