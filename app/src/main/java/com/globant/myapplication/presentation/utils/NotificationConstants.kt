package com.globant.myapplication.presentation.utils

object NotificationConstants {
    const val NOTIFICATION_REMINDER_ID = "NOTIFICATION_REMINDER_ID"
    const val NOTIFICATION_REMINDER_TITLE = "NOTIFICATION_REMINDER_TITLE"
    const val NOTIFICATION_REMINDER_DESCRIPTION = "NOTIFICATION_REMINDER_DESC"
    const val NOTIFICATION_REMINDER_TYPE = "NOTIFICATION_REMINDER_TYPE"
    const val NOTIFICATION_REMINDER_DATE = "NOTIFICATION_REMINDER_DATE"
    const val NOTIFICATION_REQUEST_CODE = 0
    const val NOTIFICATION_ID = 123
    const val NOTIFICATION_ACTION_CREATE = "com.globant.myreminders.ACTION_CREATE_REMINDER_ALARM"
    const val NOTIFICATION_ACTION_DISMISS = "com.globant.myreminders.ACTION_DISMISS_REMINDER_ALARM"
    const val NOTIFICATION_ACTION_POSTPONE = "com.globant.myreminders.ACTION_POSTPONE_REMINDER_ALARM"
    private const val NOTIFICATION_POSTPONE_MIN = 2L
    const val NOTIFICATION_POSTPONE_MILLI = NOTIFICATION_POSTPONE_MIN*60000L
}