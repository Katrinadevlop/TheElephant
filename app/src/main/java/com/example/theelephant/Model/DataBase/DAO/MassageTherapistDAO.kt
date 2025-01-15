package com.example.theelephant.Model.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theelephant.Model.DataBase.Entities.MassageTherapist

@Dao
interface MassageTherapistDAO {
    @Query("SELECT * FROM massage_therapist")
    suspend fun getMassageTherapist()

    @Query("SELECT * FROM massage_therapist WHERE id = :id")
    suspend fun setMassageTherapist(id: Int): MassageTherapist

    @Query("SELECT * FROM massage_therapist WHERE id = :id")
    suspend fun deleteMassageTherapist(id: Int)

    @Query("DELETE FROM massage_therapist")
    suspend fun deleteAllMassageTherapist()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMassageTherapist(massageTherapist: MassageTherapist)
}
