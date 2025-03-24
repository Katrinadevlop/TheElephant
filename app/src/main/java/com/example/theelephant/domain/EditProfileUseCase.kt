package com.example.theelephant.domain

import com.example.theelephant.data.model.Parent
import com.example.theelephant.domain.interfaces.ParentRepositoryInterfase

class EditProfileUseCase(private val parentRepositoryInterfase: ParentRepositoryInterfase) {
    suspend fun findParent(phone: String): Boolean {
        val parents = parentRepositoryInterfase.getAllParent()
        return parents.any { it.phone == phone }
    }

    suspend private fun findIdParent(phone: String): String {
        val parents = parentRepositoryInterfase.getAllParent()
        val findParent = parents.find { it.phone == phone }
        return "" //findParent?.id ?: ""
    }

    suspend fun updateParent(newParent: Parent): Boolean {
        return try {
            val idParent = findIdParent(newParent.phone)
            parentRepositoryInterfase.changeParent(newParent, idParent)
            true
        } catch (e: Exception) {
            false
        }
    }
}