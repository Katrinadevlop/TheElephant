package com.example.theelephant.Model.DataBase.Repository

import com.example.theelephant.Model.DataBase.DataBase
import com.example.theelephant.Model.DataBase.Entities.ScheduleEntity
import kotlinx.coroutines.flow.Flow

class ScheduleRepository (dataBase: DataBase) {
    private val scheduleDao = dataBase.ScheduleDao()

    fun getSchedule(): Flow<List<ScheduleEntity>> {
        return scheduleDao.getSchedule()
    }

    suspend fun setSchedule(id: Int) {
        scheduleDao.setSchedule(id)
    }

    suspend fun deleteSchedule(id: Int) {
        scheduleDao.deleteSchedule(id)
    }

    suspend fun deleteAllSchedule() {
        scheduleDao.deleteAllSchedule()
    }

    suspend fun insertSchedule(schedule: ScheduleEntity) {
        scheduleDao.insertSchedule(schedule)
    }
}