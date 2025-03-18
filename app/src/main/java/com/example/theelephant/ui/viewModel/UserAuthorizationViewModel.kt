package com.example.theelephant.ui.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.data.dataBase.FireBase
import com.example.theelephant.domain.UserAuthorizationRepository
import kotlinx.coroutines.launch

class UserAuthorizationViewModel(private val fireBase: FireBase) : ViewModel() {

    @SuppressLint("SuspiciousIndentation")
    fun checkParent(
        currentPhone: String,
        currentPassword: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit,
    ) {
        viewModelScope.launch {
            try {
                val isAuthorized  = UserAuthorizationRepository(fireBase).checkParent(currentPhone = currentPhone, currentPassword = currentPassword)
                    if (isAuthorized) {
                        onSuccess("Пользователь авторизован")
                    }
                    else {
                        onError("Пользователь не найден")
                    }
            } catch (e: Exception) {
                onError("Ошибка при проверке пользователя: ${e.message}")
            }
        }
    }
}