package com.example.theelephant.domain

import com.example.theelephant.data.model.Parent
import com.example.theelephant.domain.interfaces.ParentRepositoryInterfase
import com.example.theelephant.domain.interfaces.SpecialistRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRegistrationUseCase(
    private val parentRepositoryInterfase: ParentRepositoryInterfase,
    private val specialistRepositoryInterface: SpecialistRepositoryInterface,
) {
    fun isPhoneNumberValid(phone: String): Boolean {
        val regex = "^7\\d{10}$".toRegex()
        return phone.matches(regex)
    }

    suspend fun findParent(currentParent: Parent): Boolean {
        val parents = parentRepositoryInterfase.getAllParent()
        return parents.any { it.name == currentParent.name && it.surname == currentParent.surname && it.phone == currentParent.phone }
    }

    suspend fun findSpecialist(currentParent: Parent): Boolean {
        val specialists = specialistRepositoryInterface.getAllSpecialist()
        return specialists.any {
            it.name == currentParent.name && it.surname == currentParent.surname && it.phone == currentParent.phone
        }
    }

    suspend fun saveParent(currentParent: Parent): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                parentRepositoryInterfase.addParent(currentParent)
                true
            } catch (e: Exception) {
                false
            }
        }
    }
}