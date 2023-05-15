package com.globant.myapplication.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.globant.myapplication.presentation.viewmodel.CreateViewModel
import com.globant.myapplication.presentation.viewmodel.DetailViewModel

@Composable
fun CreateReminderScreen(
    viewModel: CreateViewModel,
    onExit: () -> Unit
) {
    val detailState = viewModel.state.collectAsState()

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
                    viewModel.saveReminder(reminder)
                    onExit()
                },
                onCancel = onExit
            )
        }
    }

}