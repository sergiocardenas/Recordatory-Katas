package com.globant.myapplication.data.datasource

import com.globant.myapplication.data.db.ReminderDao
import com.globant.myapplication.data.entity.ReminderEntity
import com.globant.myapplication.data.mapper.toEntity
import com.globant.myapplication.data.mapper.toModel
import com.globant.myapplication.domain.model.ReminderModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(
    private val reminderDao: ReminderDao
) : LocalDataSource{

    override suspend fun getReminderList(): Flow<List<ReminderModel>> = flow {
        val list = withContext(Dispatchers.IO) {
            reminderDao.getAllReminders().map {
                    reminderEntity ->  reminderEntity.toModel()
            }
        }
        emit(list)
    }

    override suspend fun getReminder(id: Int): Flow<ReminderModel> = flow {
        val reminder = withContext(Dispatchers.IO) {
            reminderDao.getReminder(id) ?: ReminderEntity(id = -1)
        }
        emit(reminder.toModel())
    }

    override suspend fun saveReminderList(reminder: ReminderModel) {
        withContext(Dispatchers.IO) {
            reminderDao.insertReminder(reminder = reminder.toEntity())
        }
    }

    override suspend fun getReminderCount(): Int {
        return withContext(Dispatchers.IO) {
            reminderDao.reminderCount()
        }
    }
}