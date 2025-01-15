package com.example.theelephant.Model.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theelephant.Model.DataBase.Entities.Neuropsychologist
import kotlinx.coroutines.flow.Flow

@Dao
interface NeuropsychologistDAO {
    @Query("SELECT * FROM neuropsychologist")
    fun getNeuropsychologist(): Flow<List<Neuropsychologist>>

    @Query("SELECT * FROM neuropsychologist WHERE id = :id")
    fun setNeuropsychologist(id: Int): Neuropsychologist

    @Query("SELECT * FROM neuropsychologist WHERE id = :id")
    fun deleteNeuropsychologist(id: Int)

    @Query("DELETE FROM neuropsychologist")
    fun deleteAllNeuropsychologist()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNeuropsychologist(neuropsychologist: Neuropsychologist)
}