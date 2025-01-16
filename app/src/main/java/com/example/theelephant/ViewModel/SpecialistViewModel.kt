package com.example.theelephant.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.Model.DataBase.Entities.SpecialistEntity
import com.example.theelephant.Model.DataBase.Repository.SpecialistRepository
import com.example.theelephant.Model.Specialist
import kotlinx.coroutines.launch

class SpecialistViewModel(private val specialistRepository: SpecialistRepository) : ViewModel() {

    fun saveSpecialist(name: String, surname: String, phone: String, password: String, role: Specialist.Role, specialization:String) {
        val specialist =
            SpecialistEntity(name = name, surname = surname, phone = phone, password = password, role = role.toString(), specialization = specialization)
        viewModelScope.launch {
            specialistRepository.insertSpecialist(specialist)
        }
    }

    fun getSpecialist() {
        viewModelScope.launch {
            val specialists = specialistRepository.getSpecialist()
        }
    }
}