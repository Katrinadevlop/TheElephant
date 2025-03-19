package com.example.theelephant.domain

import androidx.lifecycle.viewModelScope
import com.example.theelephant.data.model.Parent
import com.example.theelephant.data.repository.SpecialistRepository
import com.example.theelephant.domain.interfaces.ParentRepositoryInterfase
import com.example.theelephant.domain.interfaces.SpecialistRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UserRegistrationUseCase(
    private val parentRepositoryInterfase: ParentRepositoryInterfase,
    private val specialistRepositoryInterface: SpecialistRepositoryInterface,
) {
    fun isPhoneNumberValid(phone: String): Boolean {
        val regex = "^7\\s?\\d{3}\\s?\\d{3}\\d{2}\\d{2}\$".toRegex()
        return phone.matches(regex)
    }

    suspend fun findParent(currentParent: Parent): Boolean = suspendCoroutine { continuation ->
        parentRepositoryInterfase.getAllParent { parents ->
            val isFound = parents.any() {
                it.name == currentParent.name && it.surname == currentParent.surname && it.phone == currentParent.phone
            }
            continuation.resume(isFound)
        }
    }

    suspend fun findSpecialist(currentParent: Parent): Boolean = suspendCoroutine { continuation ->
        specialistRepositoryInterface.getAllSpecialist { specialists ->
            val isFound = specialists.any {
                it.name == currentParent.name && it.surname == currentParent.surname && it.phone == currentParent.phone
            }
            continuation.resume(isFound)
        }
    }

    suspend fun saveParent(currentParent: Parent): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                parentRepositoryInterfase.saveParent(currentParent)
                true
            } catch (e: Exception) {
                false
            }
        }
    }
}