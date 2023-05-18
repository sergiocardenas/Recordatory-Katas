package com.globant.myapplication.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import com.globant.myapplication.presentation.state.ReminderState
import com.globant.myapplication.presentation.state.Urgency
import com.globant.myapplication.presentation.utils.getDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailReminderEdit(
    detailState: State<ReminderState>,
    onSave: (reminder: ReminderState) -> Unit,
    onCancel: () -> Unit
) {
    var showDialog by remember {mutableStateOf(false)}
    var showDropList by remember {mutableStateOf(false)}
    val expandedIndex = remember { mutableStateOf(-1) }

    var newTitle by remember {mutableStateOf(detailState.value.title)}
    var newDescription by remember {mutableStateOf(detailState.value.description)}
    var newDate by remember { mutableStateOf(Date(detailState.value.date)) }
    var newType by remember {mutableStateOf(detailState.value.type)}

    OutlinedTextField(
        value = newTitle,
        onValueChange = { newTitle = it },
        label = { Text("Name") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = newDescription,
        onValueChange = { newDescription = it },
        label = { Text("Description") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = getDateFormat().format(newDate),
        onValueChange = { },
        label = { Text("Date") },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                if (it.hasFocus) {
                    showDialog = true
                }
            }
    )
    if(showDialog) {
        // Date picker
        ReminderDateTimePicker(
            currentTime = newDate.time,
            onDateTimePicked = {new ->
                newDate = Date(new)
            },
            onDismiss = {
                showDialog = false
            }
        )
    }
    if(!showDropList){
        OutlinedTextField(
            value = newType.name,
            onValueChange = { },
            label = { Text("Type") },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    if (it.hasFocus)
                        showDropList = true
                }
        )
    }else{
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ){
            DropdownMenu(
                expanded = true,
                onDismissRequest = {
                    showDropList = false
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Urgency.values().forEachIndexed { index, option ->
                    DropdownMenuItem(
                        text = {Text(option.name)},
                        onClick = {
                            // Handle menu item click here
                            expandedIndex.value = index
                            newType = option
                            showDropList = false
                        }

                    )
                }
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = { onSave(
                ReminderState(
                    id = detailState.value.id,
                    title = newTitle,
                    description = newDescription,
                    date = newDate.time,
                    type = newType
                )
            ) }) {
                Text(text = "Save")
            }
            Button(onClick = onCancel) {
                Text(text = "Cancel")
            }
        }

    }
}