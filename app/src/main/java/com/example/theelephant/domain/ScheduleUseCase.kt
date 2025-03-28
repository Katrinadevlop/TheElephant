package com.example.theelephant.domain

import com.example.theelephant.data.model.Specialist
import com.example.theelephant.domain.interfaces.ScheduleInterface
import com.example.theelephant.domain.interfaces.SpecialistRepositoryInterface

class ScheduleUseCase(
    private val scheduleInterface: ScheduleInterface,
    private val specialistRepositoryInterface: SpecialistRepositoryInterface,
) {
    suspend fun getAllSpecialist(): List<Specialist> {
        return specialistRepositoryInterface.getAllSpecialist()
    }
}