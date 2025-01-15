package com.example.theelephant.Model.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theelephant.Model.DataBase.Entities.SpeechTherapist
import kotlinx.coroutines.flow.Flow

@Dao
interface SpeechTherapistDAO {
    @Query("SELECT * FROM speech_therapist")
    fun getSpeechTherapist(): Flow<List<SpeechTherapist>>

    @Query("SELECT * FROM speech_therapist WHERE id = :id")
    fun setSpeechTherapist(id: Int): SpeechTherapist

    @Query("SELECT * FROM speech_therapist WHERE id = :id")
    fun deleteSpeechTherapist(id: Int)

    @Query("DELETE FROM speech_therapist")
    fun deleteAllSpeechTherapist()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpeechTherapist(speechTherapist: SpeechTherapist)
}
