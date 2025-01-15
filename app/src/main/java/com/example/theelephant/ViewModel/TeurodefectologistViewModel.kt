package com.example.theelephant.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.Model.DataBase.Entities.Тeurodefectologist
import com.example.theelephant.Model.DataBase.Repository.TeurodefectologistRepository
import kotlinx.coroutines.launch

class ТeurodefectologistViewModel(private val teurodefectologistRepository: TeurodefectologistRepository) :
    ViewModel() {
    private val _teurodefectologistData = MutableLiveData<List<Тeurodefectologist>>()
    val teurodefectologistData: LiveData<List<Тeurodefectologist>> get() = _teurodefectologistData

    fun fetchТeurodefectologist() {
        viewModelScope.launch {
            val teurodefectologist = teurodefectologistRepository.getТeurodefectologist()
            _teurodefectologistData.postValue(teurodefectologist)
        }
    }
}