package com.globant.myapplication.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.globant.myapplication.R
import com.globant.myapplication.presentation.state.ReminderState
import com.globant.myapplication.presentation.state.Urgency

@Composable
fun HomeReminderItem(
    reminderItem: ReminderState,
    onItemClicked: () -> Unit
) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(16.dp)
            .clickable{ onItemClicked() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            val urgencyIC = if(reminderItem.type == Urgency.URGENT){
                R.drawable.ic_exclamation_urgent
            }else{
                R.drawable.ic_exclamation
            }
            Image(
                painter = painterResource(id = urgencyIC),
                contentDescription = reminderItem.title,
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 10.dp, top = 5.dp, bottom = 5.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                    .weight(1f)
            ) {
                Text(text = reminderItem.title, style = MaterialTheme.typography.h6)
                Text(text = reminderItem.description, style = MaterialTheme.typography.body2)
            }
        }
    }
}