package com.example.theelephant.Model.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theelephant.Model.DataBase.Entities.SpecialistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SpecialistDAO {
    @Query("SELECT * FROM specialist")
    fun getSpecialist(): Flow<List<SpecialistEntity>>

    @Query("SELECT * FROM specialist WHERE id = :id")
    suspend fun setSpecialist(id: Int): SpecialistEntity

    @Query("DELETE FROM specialist WHERE id = :id")
    suspend fun deleteSpecialist(id: Int)

    @Query("DELETE FROM specialist")
    suspend fun deleteAllSpecialist()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecialist(specialist: SpecialistEntity)
}
