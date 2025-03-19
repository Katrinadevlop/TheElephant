package com.example.theelephant.domain.interfaces

import com.example.theelephant.data.model.Parent
import com.example.theelephant.data.model.Specialist

interface SpecialistRepositoryInterface {

    fun saveSpecialist(specialist: Specialist)

    fun changeSpecialist(specialist: Specialist, specialistId: String, onComplete: (Boolean) -> Unit, )

    fun getSpecialist(specialistId: String, onComplete: (Specialist?) -> Unit)

    fun getAllSpecialist(onComplete: (List<Specialist>) -> Unit)

    fun getParentByPhone(phone: String, callback: (Parent?) -> Unit)
}