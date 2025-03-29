package com.example.theelephant.domain.interfaces

import com.example.theelephant.data.model.Schedule

interface ScheduleInterface {
    suspend fun addSchedule(schedule: Schedule)
    suspend fun changeSchedule(scheduleId: String, schedule: Schedule): Boolean
    suspend fun getAllSchedule(): List<Schedule>
    suspend fun getScheduleById(scheduleId: String, onComplete: (Schedule?) -> Unit)
    suspend fun deleteSchedule(scheduleId: String, onComplete: (String?) -> Unit)
}