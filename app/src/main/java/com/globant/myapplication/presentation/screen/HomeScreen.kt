package com.globant.myapplication.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.globant.myapplication.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onItemClicked: () -> Unit,
    onAddItem: () -> Unit,
) {
    val homeState = viewModel.state.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("My Events") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddItem ) {
                Icon(Icons.Default.Add, contentDescription = "Add Item")
            }
        }
    ) { paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            items(homeState.value.reminderList) { reminder ->
                HomeReminderItem(
                    reminderItem = reminder,
                    onItemClicked = onItemClicked
                )
            }
        }
    }
}