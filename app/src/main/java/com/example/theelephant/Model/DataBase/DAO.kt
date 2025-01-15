package com.example.theelephant.Model.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theelephant.Model.User

@Dao
interface DAO {
    //Parent
    @Query("SELECT * FROM parent")
    suspend fun getParent()

    @Query("SELECT * FROM parent WHERE id = :id")
    suspend fun setParent(id: Int): User.Parent

    @Query("SELECT * FROM parent WHERE id = :id")
    suspend fun deleteParent(id:Int)

    @Query("DELETE FROM parent")
    suspend fun deleteAllParent()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParent(user: User.Parent)

    //MassageTherapist
    @Query("SELECT * FROM massage_therapist")
    suspend fun getMassageTherapist()

    @Query("SELECT * FROM massage_therapist WHERE id = :id")
    suspend fun setMassageTherapist(id: Int): User.Parent

    @Query("SELECT * FROM massage_therapist WHERE id = :id")
    suspend fun deleteMassageTherapist(id:Int)

    @Query("DELETE FROM massage_therapist")
    suspend fun deleteAllMassageTherapist()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMassageTherapist(user: User.MassageTherapist)

    //Neuropsychologist
    @Query("SELECT * FROM neuropsychologist")
    suspend fun getNeuropsychologist()

    @Query("SELECT * FROM neuropsychologist WHERE id = :id")
    suspend fun setNeuropsychologist(id: Int): User.Parent

    @Query("SELECT * FROM neuropsychologist WHERE id = :id")
    suspend fun deleteNeuropsychologist(id:Int)

    @Query("DELETE FROM neuropsychologist")
    suspend fun deleteAllNeuropsychologist()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNeuropsychologist(user: User.Neuropsychologist)

    //Psychologist
    @Query("SELECT * FROM psychologist")
    suspend fun getPsychologist()

    @Query("SELECT * FROM psychologist WHERE id = :id")
    suspend fun setPsychologist(id: Int): User.Parent

    @Query("SELECT * FROM psychologist WHERE id = :id")
    suspend fun deletePsychologist(id:Int)

    @Query("DELETE FROM psychologist")
    suspend fun deleteAllPsychologist()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPsychologist(user: User.Psychologist)

    //SpeechTherapist
    @Query("SELECT * FROM speech_therapist")
    suspend fun getSpeechTherapist()

    @Query("SELECT * FROM speech_therapist WHERE id = :id")
    suspend fun setSpeechTherapist(id: Int): User.Parent

    @Query("SELECT * FROM speech_therapist WHERE id = :id")
    suspend fun deleteSpeechTherapist(id:Int)

    @Query("DELETE FROM speech_therapist")
    suspend fun deleteAllSpeechTherapist()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpeechTherapist(user: User.SpeechTherapist)

    //Tomatis
    @Query("SELECT * FROM tomatis")
    suspend fun getTomatis()

    @Query("SELECT * FROM tomatis WHERE id = :id")
    suspend fun setTomatis(id: Int): User.Parent

    @Query("SELECT * FROM tomatis WHERE id = :id")
    suspend fun deleteTomatis(id:Int)

    @Query("DELETE FROM tomatis")
    suspend fun deleteAllTomatis()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTomatis(user: User.Tomatis)

    //Тeurodefectologist
    @Query("SELECT * FROM teurodefectologist")
    suspend fun getТeurodefectologist()

    @Query("SELECT * FROM teurodefectologist WHERE id = :id")
    suspend fun setТeurodefectologist(id: Int): User.Parent

    @Query("SELECT * FROM teurodefectologist WHERE id = :id")
    suspend fun deleteТeurodefectologist(id:Int)

    @Query("DELETE FROM teurodefectologist")
    suspend fun deleteAllТeurodefectologist()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertТeurodefectologist(user: User.Тeurodefectologist)
}