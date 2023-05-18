package com.globant.myapplication.presentation.utils.notification

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.globant.myapplication.presentation.state.ReminderState
import com.globant.myapplication.presentation.utils.NotificationConstants.NOTIFICATION_ACTION_CREATE
import com.globant.myapplication.presentation.utils.NotificationConstants.NOTIFICATION_REMINDER_DATE
import com.globant.myapplication.presentation.utils.NotificationConstants.NOTIFICATION_REMINDER_DESCRIPTION
import com.globant.myapplication.presentation.utils.NotificationConstants.NOTIFICATION_REMINDER_ID
import com.globant.myapplication.presentation.utils.NotificationConstants.NOTIFICATION_REMINDER_TITLE
import com.globant.myapplication.presentation.utils.NotificationConstants.NOTIFICATION_REMINDER_TYPE
import com.globant.myapplication.presentation.utils.NotificationConstants.NOTIFICATION_REQUEST_CODE

@SuppressLint("UnspecifiedImmutableFlag")
fun scheduleReminderAlarm(reminder: ReminderState, context: Context) {
    val reminderIntent = setIntentWithReminder(
        reminder = reminder,
        context = context,
        cls = ReminderAlarmBroadcastReceiver::class.java)

    reminderIntent.action = NOTIFICATION_ACTION_CREATE
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        NOTIFICATION_REQUEST_CODE + reminder.id,
        reminderIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP,
        reminder.date,
        pendingIntent
    )
}

fun setIntentWithReminder(reminder: ReminderState, context: Context, cls: Class<*>): Intent{
    val reminderIntent = Intent(context, cls)
    reminderIntent.apply {
        putExtra(NOTIFICATION_REMINDER_ID, reminder.id)
        putExtra(NOTIFICATION_REMINDER_TITLE, reminder.title)
        putExtra(NOTIFICATION_REMINDER_DESCRIPTION, reminder.description)
        putExtra(NOTIFICATION_REMINDER_TYPE, reminder.type.name)
        putExtra(NOTIFICATION_REMINDER_DATE, reminder.date)
    }
    return reminderIntent
}