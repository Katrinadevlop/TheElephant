package com.example.theelephant.Model.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theelephant.Model.DataBase.Entities.Tomatis

@Dao
interface TomatisDAO {
    @Query("SELECT * FROM tomatis")
    suspend fun getTomatis()

    @Query("SELECT * FROM tomatis WHERE id = :id")
    suspend fun setTomatis(id: Int): Tomatis

    @Query("SELECT * FROM tomatis WHERE id = :id")
    suspend fun deleteTomatis(id: Int)

    @Query("DELETE FROM tomatis")
    suspend fun deleteAllTomatis()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTomatis(tomatis: Tomatis)
}
