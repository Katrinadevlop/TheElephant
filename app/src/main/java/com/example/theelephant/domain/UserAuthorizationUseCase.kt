package com.example.theelephant.domain

import com.example.theelephant.domain.interfaces.ParentRepositoryInterfase

class UserAuthorizationUseCase(private val parentRepositoryInterfase: ParentRepositoryInterfase) {
    suspend fun checkParent(currentPhone: String, currentPassword: String): Boolean {
        val parents = parentRepositoryInterfase.getAllParent()
        return parents.any { it.phone == currentPhone && it.password == currentPassword }
    }
}
