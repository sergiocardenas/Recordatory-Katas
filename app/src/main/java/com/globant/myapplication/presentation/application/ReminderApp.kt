package com.globant.myapplication.presentation.application

import android.app.Application
import com.globant.myapplication.presentation.utils.notification.NotificationReminderHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ReminderApp: Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val notificationChannelHelper = NotificationReminderHelper(this)
        notificationChannelHelper.createNotificationChannel()
    }
}