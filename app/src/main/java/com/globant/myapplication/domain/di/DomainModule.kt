package com.globant.myapplication.domain.di

import com.globant.myapplication.data.datasource.LocalDataSource
import com.globant.myapplication.domain.usecase.ReminderUseCase
import com.globant.myapplication.domain.usecase.ReminderUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
     @Provides
     fun provideReminderUseCase(localDataSource: LocalDataSource): ReminderUseCase{
         return ReminderUseCaseImp(localDataSource)
     }
}