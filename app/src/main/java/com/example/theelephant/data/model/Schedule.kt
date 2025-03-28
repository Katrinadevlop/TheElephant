package com.example.theelephant.data.model

data class Schedule(
    val id: String = "",
    val date: String,
    val time: String,
    val specialistId: String,
    val parentId: String
)