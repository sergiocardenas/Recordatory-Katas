package com.globant.myapplication.data.datasource

import com.globant.myapplication.domain.model.ReminderModel
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun getReminderList(): Flow<List<ReminderModel>>
    suspend fun getReminder(id: Int): Flow<ReminderModel>
    suspend fun saveReminderList(reminder: ReminderModel)
    suspend fun getReminderCount(): Int
}