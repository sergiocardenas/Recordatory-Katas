package com.globant.myapplication.domain.usecase


import com.globant.myapplication.data.datasource.LocalDataSource
import com.globant.myapplication.domain.model.ReminderModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReminderUseCaseImp @Inject constructor(
    private val local : LocalDataSource
): ReminderUseCase {
    override suspend fun getReminderList(): Flow<List<ReminderModel>> {
        return local.getReminderList()
    }

    override suspend fun getReminder(id: Int): Flow<ReminderModel> {
        return local.getReminder(id)
    }

    override suspend fun updateReminderList(count: Int): Flow<List<ReminderModel>> {
        val currentCount = local.getReminderCount()
        return if(currentCount != count){
            local.getReminderList()
        }else{
            flow {
                emit(listOf())
            }
        }
    }

    override suspend fun saveReminderList(reminder: ReminderModel) {
        local.saveReminderList(reminder = reminder)
    }

}