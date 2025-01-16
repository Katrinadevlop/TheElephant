package com.example.theelephant.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.Model.DataBase.Entities.ParentEntity
import com.example.theelephant.Model.DataBase.Repository.ParentRepository
import kotlinx.coroutines.launch

class ParentViewModel(private val parentRepository: ParentRepository) : ViewModel() {

    fun saveParent(name: String, surname: String, phone: String, password: String) {
        val parent = ParentEntity(name = name, surname = surname, phone = phone, password = password)
        viewModelScope.launch {
            parentRepository.insertParent(parent)
        }
    }

    fun getParents() {
        viewModelScope.launch {
            val parents = parentRepository.getParent()
        }
    }
}