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
import com.globant.myapplication.presentation.viewmodel.DetailViewModel

@Composable
fun DetailReminderScreen(
    viewModel: DetailViewModel,
    onBackPressed: () -> Unit
) {
    val detailState = viewModel.state.collectAsState()
    val editMode = viewModel.editMode.collectAsState()

    Scaffold(
        topBar = { TopAppBar(
            title = { Text("My Reminder") },
            navigationIcon = {
                IconButton(onClick = {
                    if(editMode.value){
                        viewModel.changeEditMode()
                    }else{
                        onBackPressed()
                    }
                }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        ) },
        floatingActionButton = {
            if(!editMode.value){
                FloatingActionButton(onClick = { viewModel.changeEditMode() } ) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                }
            }
        }
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
            if(editMode.value){
                DetailReminderEdit(
                    detailState = detailState,
                    onSave = {reminder ->
                        viewModel.updateReminder(reminder)
                        viewModel.changeEditMode()
                    },
                    onCancel = {
                        viewModel.changeEditMode()
                    })
            }else{
                DetailReminderDisplay(detailState = detailState)
            }
        }
    }

}