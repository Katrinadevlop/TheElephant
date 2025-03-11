package com.example.theelephant.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.Model.DataBase.FireBase
import kotlinx.coroutines.launch

class UserAuthorizationViewModel(private val fireBase: FireBase) : ViewModel() {

    fun checkParent(
        currentPhone: String,
        currentPassword: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit,
    ) {
        viewModelScope.launch {
            try {
                fireBase.getAllParent { parents ->
                    val parent = parents.find {
                        it.phone == currentPhone && it.password == currentPassword
                    }

                    if (parent != null)
                        onSuccess("Пользователь авторизован")
                    else onError("Пользователь не найден")
                }
            } catch (e: Exception) {
                onError("Ошибка при проверке пользователя: ${e.message}")
            }
        }
    }
}