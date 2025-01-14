package com.example.theelephant.Model.DataBase.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parent")
data class Parent (
    @PrimaryKey var id:Int,
    val name: String,
    val surname: String,
    val phone: String,
    val password: String,
    val role: String
)