package com.example.theelephant.domain

import android.util.Log
import com.example.theelephant.data.repository.ParentRepository
import com.example.theelephant.data.repository.SpecialistRepository
import com.example.theelephant.domain.interfaces.ParentRepositoryInterfase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume

class UserAuthorizationUseCase(private val parentRepositoryInterfase: ParentRepositoryInterfase) {
    suspend fun checkParent(currentPhone: String, currentPassword: String): Boolean {
        return withContext(Dispatchers.IO) {
            suspendCancellableCoroutine { continuation ->
                parentRepositoryInterfase.getAllParent { parents ->
                    val isParentFound = parents.any {
                        it.phone == currentPhone && it.password == currentPassword
                    }
                    continuation.resume(isParentFound)
                }
            }
        }
    }
}