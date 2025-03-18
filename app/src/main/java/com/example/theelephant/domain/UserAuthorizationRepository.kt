package com.example.theelephant.domain

import android.util.Log
import com.example.theelephant.data.dataBase.FireBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume

class UserAuthorizationRepository(private val fireBase: FireBase) {
    suspend fun checkParent(currentPhone: String, currentPassword: String): Boolean {
        return withContext(Dispatchers.IO) {
            suspendCancellableCoroutine { continuation ->
                fireBase.getAllParent { parents ->
                    val isParentFound = parents.any {
                        it.phone == currentPhone && it.password == currentPassword
                    }

                    Log.e("errors", "Parents: $parents, Found: $isParentFound")

                    continuation.resume(isParentFound)
                }
            }
        }
    }
}