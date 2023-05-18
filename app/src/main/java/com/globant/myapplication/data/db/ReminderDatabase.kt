package com.globant.myapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.globant.myapplication.data.entity.ReminderEntity

@Database(entities = [ReminderEntity::class], version = 1)
abstract class ReminderDatabase : RoomDatabase() {
    abstract fun ReminderDao(): ReminderDao
}