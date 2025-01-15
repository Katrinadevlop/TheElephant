package com.example.theelephant.Model.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theelephant.Model.DataBase.Entities.Psychologist
import kotlinx.coroutines.flow.Flow

@Dao
interface PsychologistDAO {
    @Query("SELECT * FROM psychologist")
    fun getPsychologist(): Flow<List<Psychologist>>

    @Query("SELECT * FROM psychologist WHERE id = :id")
    fun setPsychologist(id: Int): Psychologist

    @Query("DELETE FROM psychologist WHERE id = :id")
    fun deletePsychologist(id: Int)

    @Query("DELETE FROM psychologist")
    fun deleteAllPsychologist()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPsychologist(psychologist: Psychologist)
}
