package com.globant.myapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.globant.myapplication.data.entity.ReminderEntity

@Dao
interface ReminderDao {
    @Query("SELECT COUNT(*) FROM ReminderEntity")
    fun reminderCount(): Int

    @Query("SELECT * FROM ReminderEntity")
    fun getAllReminders(): List<ReminderEntity>

    @Query("SELECT * FROM ReminderEntity WHERE id = :reminderId")
    fun getReminder(reminderId: Int): ReminderEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReminder(reminder: ReminderEntity)
}