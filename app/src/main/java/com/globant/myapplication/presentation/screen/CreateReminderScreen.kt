package com.globant.myapplication.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.globant.myapplication.presentation.viewmodel.CreateViewModel

@Composable
fun CreateReminderScreen(
    viewModel: CreateViewModel,
    onExit: () -> Unit
) {
    val detailState = viewModel.state.collectAsState()
    val context = LocalContext.current.applicationContext

    Scaffold(
        topBar = { TopAppBar(
            title = { Text("My New Reminder") },
        ) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = paddingValues.calculateBottomPadding(),
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            DetailReminderEdit(
                detailState = detailState,
                onSave = {reminder ->
                    viewModel.saveReminder(reminder, context)
                    onExit()
                },
                onCancel = onExit
            )
        }
    }

}