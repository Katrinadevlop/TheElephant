package com.example.theelephant.domain.interfaces

import com.example.theelephant.data.model.Schedule

interface ScheduleInterface {
    suspend fun getAllSchedule():List<Schedule>
    suspend fun getScheduleById(scheduleId: Int):Schedule
    suspend fun deleteSchedule(scheduleId: Int)
    suspend fun changeSchedule(scheduleId: Int)
    suspend fun addSSchedule(schedule: Schedule)
}