package com.example.theelephant.Model.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theelephant.Model.DataBase.Entities.Tomatis
import kotlinx.coroutines.flow.Flow

@Dao
interface TomatisDAO {
    @Query("SELECT * FROM tomatis")
    fun getTomatis(): Flow<List<Tomatis>>

    @Query("SELECT * FROM tomatis WHERE id = :id")
    fun setTomatis(id: Int): Tomatis

    @Query("DELETE FROM tomatis WHERE id = :id")
    fun deleteTomatis(id: Int)

    @Query("DELETE FROM tomatis")
    fun deleteAllTomatis()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTomatis(tomatis: Tomatis)
}
