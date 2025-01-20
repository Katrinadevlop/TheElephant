package com.example.theelephant.Model.DataBase.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "specialist_entity")
data class ScheduleEntity (
    @PrimaryKey (autoGenerate = true) val id:Int = 0,
    val date: String,
    val time: String,
    val specialistId: Int
)