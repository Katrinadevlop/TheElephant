package com.example.theelephant.presentation.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.data.repository.ParentRepository
import com.example.theelephant.data.repository.SpecialistRepository
import com.example.theelephant.domain.UserAuthorizationUseCase
import com.example.theelephant.domain.interfaces.ParentRepositoryInterfase
import kotlinx.coroutines.launch

class UserAuthorizationViewModel(private val parentRepositoryInterfase: ParentRepositoryInterfase) :
    ViewModel() {

    @SuppressLint("SuspiciousIndentation")
    fun checkParent(
        currentPhone: String,
        currentPassword: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit,
    ) {
        viewModelScope.launch {
            try {
                val isAuthorized = UserAuthorizationUseCase(parentRepositoryInterfase).checkParent(
                    currentPhone = currentPhone,
                    currentPassword = currentPassword
                )
                if (isAuthorized) {
                    onSuccess("Пользователь авторизован")
                } else {
                    onError("Пользователь не найден")
                }
            } catch (e: Exception) {
                onError("Ошибка при проверке пользователя: ${e.message}")
            }
        }
    }
}