package com.example.theelephant.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.Model.DataBase.Repository.ParentRepository
import kotlinx.coroutines.launch

class UserAuthorizationViewModel(private val parentRepository: ParentRepository) : ViewModel() {

    fun checkParent(
        currentPhone: String,
        currentPassword: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    ) {
        viewModelScope.launch {
            try {
                parentRepository.getParent().collect { parents ->
                    val parent = parents.find {
                        it.phone == currentPhone && it.password == currentPassword
                    }

                    if (parent != null)
                        onSuccess()
                    else onError("Родитель не найден")
                }
            } catch (e: Exception) {
                onError("Ошибка при проверке родителя: ${e.message}")
            }
        }
    }
}