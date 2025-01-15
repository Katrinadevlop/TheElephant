package com.example.theelephant.Model.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theelephant.Model.DataBase.Entities.Parent

@Dao
interface ParentDAO {
    @Query("SELECT * FROM parent")
    suspend fun getParent()

    @Query("SELECT * FROM parent WHERE id = :id")
    suspend fun setParent(id: Int): Parent

    @Query("SELECT * FROM parent WHERE id = :id")
    suspend fun deleteParent(id: Int)

    @Query("DELETE FROM parent")
    suspend fun deleteAllParent()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParent(parent: Parent)
}



