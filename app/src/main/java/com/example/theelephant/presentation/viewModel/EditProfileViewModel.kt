package com.example.theelephant.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.data.model.Parent
import com.example.theelephant.domain.EditProfileUseCase
import kotlinx.coroutines.launch

class EditProfileViewModel(private val editProfileUseCase: EditProfileUseCase) : ViewModel() {
    fun updateParent(
        currentParent: Parent,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit,
    ) {
        if (currentParent.name.isEmpty() || currentParent.phone.isEmpty()) {
            onError("Заполните все поля")
        }

        viewModelScope.launch {
            if (editProfileUseCase.findParent(currentParent.phone)){
                editProfileUseCase.updateParent(currentParent)
                onSuccess("Данные изменены")
            }
            else{
                onError("Не удалось изменить данные")
            }
        }
    }
}