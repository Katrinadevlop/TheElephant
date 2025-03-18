package com.example.theelephant.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.data.dataBase.FireBase
import com.example.theelephant.data.model.Parent
import kotlinx.coroutines.launch

class UserRegistrationViewModel(private val parentRepository: FireBase) : ViewModel() {

    fun isPhoneNumberValid(phone: String): Boolean {
        val regex = "^7\\s?\\d{3}\\s?\\d{3}\\d{2}\\d{2}\$".toRegex()
        return phone.matches(regex)
    }

    fun registerParent(
        parent: Parent,
        passwordRepeat: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit,
    ) {
        val name = parent.name
        val surname = parent.surname
        val phone = parent.phone
        val password = parent.password

        if (name.isBlank() || surname.isBlank() || phone.isBlank() || password.isBlank() || passwordRepeat.isBlank()) {
            onError("Не все поля заполнены")
            return
        }

        if (password != passwordRepeat) {
            onError("Пароли не совпадают")
            return
        }

        if (!isPhoneNumberValid(parent.phone)){
            onError("Введите правильный номер телефона")
            return
        }

        FireBase().getAllParent { parents ->
            val findParent = parents.find {
                it.name == parent.name && it.surname == parent.surname && it.phone == parent.phone
            }

            if (findParent != null) {
                onError("Пользователь уже зарегистрирован")
                return@getAllParent
            }

            FireBase().getAllSpecialist { specialists ->
                val findSpecialist = specialists.find {
                    it.name == parent.name && it.surname == parent.surname && it.phone == parent.phone
                }

                if (findSpecialist != null) {
                    onError("Специалист уже зарегистрирован")
                    return@getAllSpecialist
                }

                viewModelScope.launch {
                    try {
                        parentRepository.saveParent(parent)
                        onSuccess("Пользователь успешно зарегистрирован")
                    } catch (e: Exception) {
                        onError("Ошибка при сохранении данных: ${e.message}")
                    }
                }
            }
        }
    }
}