package com.example.theelephant.Model.DataBase.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parent")
data class ParentEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val surname: String,
    val phone: String,
    val password: String,
)