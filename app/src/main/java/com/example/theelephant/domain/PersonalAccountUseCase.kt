package com.example.theelephant.domain

import android.provider.ContactsContract.CommonDataKinds.Phone
import com.example.theelephant.data.model.Parent
import com.example.theelephant.data.model.Specialist
import com.example.theelephant.domain.interfaces.ParentRepositoryInterfase
import com.example.theelephant.domain.interfaces.SpecialistRepositoryInterface

class PersonalAccountUseCase(
    private var parentRepositoryInterfase: ParentRepositoryInterfase,
    private var specialistRepositoryInterface: SpecialistRepositoryInterface,
) {
    suspend fun getParent(phone: String): Parent? {
        val currentParent = parentRepositoryInterfase.getAllParent().find { phone == it.phone }
        return currentParent
    }

    suspend fun getSpeciatist(phone: String): Specialist? {
        val currentSpecialist =
            specialistRepositoryInterface.getAllSpecialist().find { phone == it.phone }
        return currentSpecialist
    }
}