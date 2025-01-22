package com.example.theelephant.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.Model.DataBase.Entities.ParentEntity
import com.example.theelephant.Model.DataBase.Repository.ParentRepository
import com.example.theelephant.Model.Parent
import com.example.theelephant.Model.SpecialistProvider
import kotlinx.coroutines.launch

class UserRegistrationViewModel(private val parentRepository: ParentRepository) : ViewModel() {

    fun registerParent(
        parent: Parent,
        passwordRepeat: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    ) {
        val name = parent.name
        val surname = parent.surname
        val phone = parent.phone
        val password = parent.password
        val specialists = SpecialistProvider()

        if (name.isBlank() || surname.isBlank() || phone.isBlank() || password.isBlank() || passwordRepeat.isBlank()) {
            onError("Не все поля заполнены")
            return
        }

        if (password != passwordRepeat) {
            onError("Пароли не совпадают")
            return
        }

        val findSpecialist = specialists.getSpecialist()
            .find { it.name == name && it.surname == surname && it.phone == phone }
        if (findSpecialist != null) {
            onError("Специалист уже зарегистрирован")
            return
        }

        val parentEntity = ParentEntity(
            name = parent.name,
            surname = parent.surname,
            phone = parent.phone,
            password = parent.password
        )

        viewModelScope.launch {
            try {
                parentRepository.insertParent(parentEntity)
                onSuccess()
            } catch (e: Exception) {
                onError("Ошибка при сохранении данных: ${e.message}")
            }
        }
    }
}