package com.example.theelephant.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.data.model.Parent
import com.example.theelephant.data.model.Specialist
import com.example.theelephant.domain.PersonalAccountUseCase
import kotlinx.coroutines.launch

class PersonalAccountViewModel(private val personalAccountUseCase: PersonalAccountUseCase) : ViewModel() {
    fun getUser(phone:String, onSuccessParent: (Parent) -> Unit, onSuccessSpecialist: (Specialist) -> Unit, onError: (String) -> Unit){
        viewModelScope.launch {
            val currentParent = personalAccountUseCase.getParent(phone)
            val currentSpecialist = personalAccountUseCase.getSpeciatist(phone)
            if (currentParent == null){
                onError("Такого родителя нет")
            }
            else {
                onSuccessParent(currentParent)
            }

            if (currentSpecialist == null){
                onError("Такого специалиста нет")
            }
            else {
                onSuccessSpecialist(currentSpecialist)
            }
        }
    }
}