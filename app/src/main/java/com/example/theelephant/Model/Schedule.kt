package com.example.theelephant.Model

import java.sql.Time
import java.util.Date

data class Schedule(
    val id:Int,
    val date: Date,
    val time:Time,
    val specialist: Specialist
)