package com.example.theelephant.Model.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theelephant.Model.DataBase.Entities.Тeurodefectologist

@Dao
interface ТeurodefectologistDAO {
    @Query("SELECT * FROM teurodefectologist")
    suspend fun getТeurodefectologist()

    @Query("SELECT * FROM teurodefectologist WHERE id = :id")
    suspend fun setТeurodefectologist(id: Int): Тeurodefectologist

    @Query("SELECT * FROM teurodefectologist WHERE id = :id")
    suspend fun deleteТeurodefectologist(id:Int)

    @Query("DELETE FROM teurodefectologist")
    suspend fun deleteAllТeurodefectologist()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertТeurodefectologist(teurodefectologist: Тeurodefectologist)
}