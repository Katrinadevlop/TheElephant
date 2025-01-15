package com.example.theelephant.Model.DataBase.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tomatis")
data class Tomatis(
    @PrimaryKey val id: Int,
    val name: String,
    val surname: String,
    val phone: String,
    val password: String,
    val role: String,
    val specialization: String,
)