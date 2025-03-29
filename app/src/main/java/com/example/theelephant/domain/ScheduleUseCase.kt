package com.example.theelephant.domain

import com.example.theelephant.data.model.Schedule
import com.example.theelephant.data.model.Specialist
import com.example.theelephant.domain.interfaces.ScheduleInterface
import com.example.theelephant.domain.interfaces.SpecialistRepositoryInterface

class ScheduleUseCase(
    private val scheduleInterface: ScheduleInterface,
    private val specialistRepositoryInterface: SpecialistRepositoryInterface,
) {
    suspend fun saveRecord(schedule:Schedule) {
        return scheduleInterface.addSchedule(schedule)
    }

    suspend fun getAllSpecialist(): List<Specialist> {
        return specialistRepositoryInterface.getAllSpecialist()
    }
}