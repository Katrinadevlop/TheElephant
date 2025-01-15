package com.example.theelephant.Model.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theelephant.Model.DataBase.Entities.Тeurodefectologist
import kotlinx.coroutines.flow.Flow

@Dao
interface ТeurodefectologistDAO {
    @Query("SELECT * FROM teurodefectologist")
    fun getТeurodefectologist(): Flow<List<Тeurodefectologist>>

    @Query("SELECT * FROM teurodefectologist WHERE id = :id")
    fun setТeurodefectologist(id: Int): Тeurodefectologist

    @Query("DELETE FROM teurodefectologist WHERE id = :id")
    fun deleteТeurodefectologist(id: Int)

    @Query("DELETE FROM teurodefectologist")
    fun deleteAllТeurodefectologist()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertТeurodefectologist(teurodefectologist: Тeurodefectologist)
}