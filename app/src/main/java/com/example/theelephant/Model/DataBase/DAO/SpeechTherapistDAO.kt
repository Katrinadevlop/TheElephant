package com.example.theelephant.Model.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theelephant.Model.DataBase.Entities.SpeechTherapist

@Dao
interface SpeechTherapistDAO {
    @Query("SELECT * FROM speech_therapist")
    suspend fun getSpeechTherapist()

    @Query("SELECT * FROM speech_therapist WHERE id = :id")
    suspend fun setSpeechTherapist(id: Int): SpeechTherapist

    @Query("SELECT * FROM speech_therapist WHERE id = :id")
    suspend fun deleteSpeechTherapist(id: Int)

    @Query("DELETE FROM speech_therapist")
    suspend fun deleteAllSpeechTherapist()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpeechTherapist(speechTherapist: SpeechTherapist)
}
