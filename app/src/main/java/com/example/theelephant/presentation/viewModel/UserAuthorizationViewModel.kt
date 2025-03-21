package com.example.theelephant.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.domain.UserAuthorizationUseCase
import kotlinx.coroutines.launch

class UserAuthorizationViewModel(private val userAuthorizationUseCase: UserAuthorizationUseCase) :
    ViewModel() {

    fun checkParent(
        currentPhone: String,
        currentPassword: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit,
    ) {
        viewModelScope.launch {
            try {
                val isAuthorized = userAuthorizationUseCase.checkParent(
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