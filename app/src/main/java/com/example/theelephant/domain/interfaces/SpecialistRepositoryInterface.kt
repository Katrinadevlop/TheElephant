package com.example.theelephant.domain.interfaces

import com.example.theelephant.data.model.Specialist

interface SpecialistRepositoryInterface {
    suspend fun addSpecialist(specialist: Specialist)
    suspend fun changeSpecialist(specialist: Specialist, specialistId: String): Boolean
    suspend fun getAllSpecialist(): List<Specialist>
    suspend fun getSpecialistById(specialistId: String, onComplete: (Specialist?) -> Unit)
}