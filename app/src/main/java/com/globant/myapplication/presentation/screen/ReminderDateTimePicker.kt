package com.globant.myapplication.presentation.screen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun ReminderDateTimePicker(
    currentTime: Long,
    onDateTimePicked: (Long) -> Unit,
    onDismiss: () -> Unit
) {

    var canceled by remember {mutableStateOf(false)}
    val currentCalendar by remember {mutableStateOf(Calendar.getInstance())}
    currentCalendar.timeInMillis = currentTime
    var newYear by remember {mutableStateOf(currentCalendar[Calendar.YEAR])}
    var newMonth by remember {mutableStateOf(currentCalendar[Calendar.MONTH])}
    var newDay by remember {mutableStateOf(currentCalendar[Calendar.DAY_OF_MONTH])}

    val datePicker = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            newYear = selectedYear
            newMonth = selectedMonth
            newDay = selectedDayOfMonth
        },
        currentCalendar[Calendar.YEAR],
        currentCalendar[Calendar.MONTH],
        currentCalendar[Calendar.DAY_OF_MONTH]
    )
    val timePicker = TimePickerDialog(
        LocalContext.current,
        { _, selectedHour: Int, selectedMinute: Int ->
            currentCalendar.set(
                newYear, newMonth, newDay, selectedHour, selectedMinute
            )
        },
        currentCalendar[Calendar.HOUR],
        currentCalendar[Calendar.MINUTE],
        false
    )

    datePicker.setOnCancelListener { canceled = true }
    timePicker.setOnCancelListener{ canceled = true }
    datePicker.setOnDismissListener {
        if(canceled){
            onDismiss()
        }else{
            timePicker.show()
        }
    }
    timePicker.setOnDismissListener {
        if(canceled){
            onDismiss()
        }else{
            onDateTimePicked(currentCalendar.timeInMillis)
            onDismiss()
        }
    }
    datePicker.show()

}