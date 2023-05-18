package com.globant.myapplication.presentation.utils.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.globant.myapplication.presentation.state.ReminderState
import com.globant.myapplication.presentation.state.Urgency
import com.globant.myapplication.presentation.utils.NotificationConstants
import com.globant.myapplication.presentation.utils.NotificationConstants.NOTIFICATION_ACTION_CREATE
import com.globant.myapplication.presentation.utils.NotificationConstants.NOTIFICATION_ACTION_DISMISS
import com.globant.myapplication.presentation.utils.NotificationConstants.NOTIFICATION_ACTION_POSTPONE
import com.globant.myapplication.presentation.utils.NotificationConstants.NOTIFICATION_ID
import com.globant.myapplication.presentation.utils.NotificationConstants.NOTIFICATION_POSTPONE_MILLI
import com.globant.myapplication.presentation.utils.NotificationConstants.NOTIFICATION_REMINDER_DATE
import com.globant.myapplication.presentation.utils.NotificationConstants.NOTIFICATION_REMINDER_DESCRIPTION
import com.globant.myapplication.presentation.utils.NotificationConstants.NOTIFICATION_REMINDER_ID
import com.globant.myapplication.presentation.utils.NotificationConstants.NOTIFICATION_REMINDER_TITLE
import com.globant.myapplication.presentation.utils.NotificationConstants.NOTIFICATION_REMINDER_TYPE
import com.globant.myapplication.presentation.utils.notification.NotificationReminderHelper.Companion.CHANNEL_ID
import com.globant.myreminders.R
import java.util.Calendar

class ReminderAlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(intent.action.isNullOrEmpty() || intent.action == NOTIFICATION_ACTION_CREATE){

            val reminder = getReminderFromIntent(intent = intent)
            val notification = getNotification(reminder, context)

            notificationManager.notify(NOTIFICATION_ID+reminder.id, notification)

        }else if (intent.action == NOTIFICATION_ACTION_POSTPONE){

            val reminderPostpone = getReminderFromIntent(intent = intent)
            scheduleReminderAlarm(
                reminder = reminderPostpone.copy(
                    date = reminderPostpone.date+ NOTIFICATION_POSTPONE_MILLI
                ),
                context = context
            )
            notificationManager.cancel(NOTIFICATION_ID+reminderPostpone.id)

        }else if (intent.action == NOTIFICATION_ACTION_DISMISS){

            val reminderPostpone = getReminderFromIntent(intent = intent)
            notificationManager.cancel(NOTIFICATION_ID+reminderPostpone.id)

        }

    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun getNotification(reminder: ReminderState, context: Context): Notification{
        val notificationView = RemoteViews("com.globant.myreminders", R.layout.reminder_notification)
        notificationView.setTextViewText(R.id.notification_title_tv, reminder.title)
        notificationView.setTextViewText(R.id.notification_description_tv,reminder.description)
        val icon = if(reminder.type == Urgency.NORMAL) R.drawable.ic_exclamation else R.drawable.ic_exclamation_urgent
        notificationView.setImageViewResource(R.id.notification_iv, icon)
        val postponeIntent = setIntentWithReminder(
            reminder = reminder,
            context = context,
            cls = ReminderAlarmBroadcastReceiver::class.java
        )
        postponeIntent.action = NOTIFICATION_ACTION_POSTPONE
        val postponePendingIntent = PendingIntent.getBroadcast(
            context,
            NotificationConstants.NOTIFICATION_REQUEST_CODE + reminder.id,
            postponeIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val dismissIntent = setIntentWithReminder(
            reminder = reminder,
            context = context,
            cls = ReminderAlarmBroadcastReceiver::class.java
        )
        dismissIntent.action = NOTIFICATION_ACTION_DISMISS
        val dismissPendingIntent = PendingIntent.getBroadcast(
            context,
            NotificationConstants.NOTIFICATION_REQUEST_CODE + reminder.id,
            dismissIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        notificationView.setOnClickPendingIntent(R.id.notification_postpone_button, postponePendingIntent)
        notificationView.setOnClickPendingIntent(R.id.notification_dismiss_button, dismissPendingIntent)

        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_exclamation)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(notificationView)
            .setAutoCancel(true)
            .build()
    }

    private fun getReminderFromIntent(intent: Intent): ReminderState{
        val type = intent.getStringExtra(NOTIFICATION_REMINDER_TYPE) ?: Urgency.NORMAL.name
        return ReminderState(
            id = intent.getIntExtra(NOTIFICATION_REMINDER_ID, -1),
            title = intent.getStringExtra(NOTIFICATION_REMINDER_TITLE) ?: "Alarm",
            description = intent.getStringExtra(NOTIFICATION_REMINDER_DESCRIPTION) ?: "Alarm Triggered!",
            date = intent.getLongExtra(NOTIFICATION_REMINDER_DATE, Calendar.getInstance().timeInMillis),
            type = if(type == Urgency.NORMAL.name) Urgency.NORMAL else Urgency.URGENT
        )
    }
}