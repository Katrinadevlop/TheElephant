package com.example.theelephant.Model.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theelephant.Model.DataBase.Entities.Neuropsychologist

@Dao
interface NeuropsychologistDAO {
    @Query("SELECT * FROM neuropsychologist")
    suspend fun getNeuropsychologist()

    @Query("SELECT * FROM neuropsychologist WHERE id = :id")
    suspend fun setNeuropsychologist(id: Int): Neuropsychologist

    @Query("SELECT * FROM neuropsychologist WHERE id = :id")
    suspend fun deleteNeuropsychologist(id: Int)

    @Query("DELETE FROM neuropsychologist")
    suspend fun deleteAllNeuropsychologist()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNeuropsychologist(neuropsychologist: Neuropsychologist)
}