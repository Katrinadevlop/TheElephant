package com.example.theelephant.Model.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theelephant.Model.DataBase.Entities.Psychologist

@Dao
interface PsychologistDAO {
    @Query("SELECT * FROM psychologist")
    suspend fun getPsychologist()

    @Query("SELECT * FROM psychologist WHERE id = :id")
    suspend fun setPsychologist(id: Int): Psychologist

    @Query("SELECT * FROM psychologist WHERE id = :id")
    suspend fun deletePsychologist(id: Int)

    @Query("DELETE FROM psychologist")
    suspend fun deleteAllPsychologist()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPsychologist(psychologist: Psychologist)
}
