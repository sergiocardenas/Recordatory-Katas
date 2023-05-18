package com.globant.myapplication.data.di

import android.content.Context
import androidx.room.Room
import com.globant.myapplication.data.datasource.LocalDataSource
import com.globant.myapplication.data.datasource.LocalDataSourceImp
import com.globant.myapplication.data.db.ReminderDao
import com.globant.myapplication.data.db.ReminderDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule() {

    @Singleton
    @Provides
    fun provideReminderDB(@ApplicationContext appContext: Context): ReminderDatabase {
        return Room.databaseBuilder(
            appContext,
            ReminderDatabase::class.java,
            "reminder-database"
        ).allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun provideReminderDao(database: ReminderDatabase) = database.ReminderDao()


    @Singleton
    @Provides
    fun provideLocalDataSource(reminderDao: ReminderDao) : LocalDataSource {
        return LocalDataSourceImp(reminderDao)
    }
}