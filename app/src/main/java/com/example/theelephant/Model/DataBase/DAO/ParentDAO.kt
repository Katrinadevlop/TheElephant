package com.example.theelephant.Model.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theelephant.Model.DataBase.Entities.Parent
import kotlinx.coroutines.flow.Flow

@Dao
interface ParentDAO {
    @Query("SELECT * FROM parent")
    fun getParent(): Flow<List<Parent>>

    @Query("SELECT * FROM parent WHERE id = :id")
    fun setParent(id: Int): Parent

    @Query("DELETE FROM parent WHERE id = :id")
    fun deleteParent(id: Int)

    @Query("DELETE FROM parent")
    fun deleteAllParent()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertParent(parent: Parent)
}



