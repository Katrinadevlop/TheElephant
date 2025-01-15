package com.example.theelephant.Model.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theelephant.Model.DataBase.Entities.MassageTherapist
import kotlinx.coroutines.flow.Flow

@Dao
interface MassageTherapistDAO {
    @Query("SELECT * FROM massage_therapist")
    fun getMassageTherapist(): Flow<List<MassageTherapist>>

    @Query("SELECT * FROM massage_therapist WHERE id = :id")
    fun setMassageTherapist(id: Int): MassageTherapist

    @Query("SELECT * FROM massage_therapist WHERE id = :id")
    fun deleteMassageTherapist(id: Int)

    @Query("DELETE FROM massage_therapist")
    fun deleteAllMassageTherapist()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMassageTherapist(massageTherapist: MassageTherapist)
}
