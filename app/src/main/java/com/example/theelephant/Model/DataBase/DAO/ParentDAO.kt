package com.example.theelephant.Model.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theelephant.Model.DataBase.Entities.ParentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ParentDAO {
    @Query("SELECT * FROM parent")
    fun getParent(): Flow<List<ParentEntity>>

    @Query("SELECT * FROM parent WHERE id = :id")
    suspend fun setParent(id: Int): ParentEntity

    @Query("DELETE FROM parent WHERE id = :id")
    suspend fun deleteParent(id: Int)

    @Query("DELETE FROM parent")
    suspend fun deleteAllParent()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParent(parent: ParentEntity)
}



