package com.example.theelephant.domain.interfaces

import com.example.theelephant.data.model.Parent
import com.example.theelephant.data.model.Specialist

interface SpecialistRepositoryInterface {

    suspend fun saveSpecialist(specialist: Specialist)

    suspend fun changeSpecialist(specialist: Specialist, specialistId: String, onComplete: (Boolean) -> Unit, )

    suspend fun getSpecialist(specialistId: String, onComplete: (Specialist?) -> Unit)

    suspend fun getAllSpecialist(): List<Specialist>

    suspend fun getParentByPhone(phone: String, callback: (Parent?) -> Unit)
}