package com.example.theelephant.domain.interfaces

import com.example.theelephant.data.model.Specialist

interface SpecialistRepositoryInterface {
    suspend fun addSpecialist(specialist: Specialist, onComplete: (String?) -> Unit)
    suspend fun changeSpecialist(specialist: Specialist, specialistId: String, onComplete: (Boolean) -> Unit, )
    suspend fun getSpecialistById(specialistId: String, onComplete: (Specialist?) -> Unit)
    suspend fun getAllSpecialist(): List<Specialist>
}