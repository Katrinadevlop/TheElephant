package com.example.theelephant.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.data.repository.SpecialistRepository
import com.example.theelephant.data.model.Parent
import com.example.theelephant.data.repository.ParentRepository
import com.example.theelephant.domain.UserRegistrationUseCase
import com.example.theelephant.domain.interfaces.ParentRepositoryInterfase
import kotlinx.coroutines.launch

class UserRegistrationViewModel(private val userRegistrationUseCase: UserRegistrationUseCase) :
    ViewModel() {
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

        if (!userRegistrationUseCase.isPhoneNumberValid(parent.phone)) {
            onError("Введите правильный номер телефона")
            return
        }

        viewModelScope.launch {
            if (userRegistrationUseCase.findParent(parent)) {
                onError("Пользователь уже зарегистрирован")
                return@launch
            }

            if (userRegistrationUseCase.findSpecialist(parent)) {
                onError("Специалист уже зарегистрирован")
                return@launch
            }

            try {
                userRegistrationUseCase.saveParent(parent)
                onSuccess("Пользователь успешно зарегистрирован")
            } catch (e: Exception) {
                onError("Ошибка при сохранении данных: ${e.message}")
            }
        }
    }
}
