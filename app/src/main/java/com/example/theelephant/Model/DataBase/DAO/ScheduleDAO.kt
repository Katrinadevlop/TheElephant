package com.example.theelephant.Model.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theelephant.Model.DataBase.Entities.ScheduleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDAO {
    @Query("SELECT * FROM specialist_entity")
    fun getSchedule(): Flow<List<ScheduleEntity>>

    @Query("SELECT * FROM specialist_entity WHERE id = :id")
    suspend fun setSchedule(id: Int): ScheduleEntity

    @Query("DELETE FROM specialist_entity WHERE id = :id")
    suspend fun deleteSchedule(id: Int)

    @Query("DELETE FROM specialist_entity")
    suspend fun deleteAllSchedule()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedule: ScheduleEntity)
}