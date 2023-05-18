package com.globant.myapplication.data.mapper

import com.globant.myapplication.data.entity.ReminderEntity
import com.globant.myapplication.domain.model.ReminderModel

fun ReminderEntity.toModel() = ReminderModel(
    id = if (id > 0) id else -1,
    title = title,
    description = description,
    date = date,
    type = type
)

fun ReminderModel.toEntity() = ReminderEntity(
    id = if (id > 0) id else 0 ,
    title = title,
    description = description,
    date = date,
    type = type
)