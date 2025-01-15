package com.example.theelephant.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.theelephant.Model.DataBase.Entities.Тeurodefectologist
import com.example.theelephant.Model.DataBase.Repository.ТeurodefectologistRepository

class ТeurodefectologistViewModel(teurodefectologistRepository: ТeurodefectologistRepository) :
    ViewModel() {
    val teurodefectologistData: LiveData<List<Тeurodefectologist>> = teurodefectologistRepository.getТeurodefectologist().asLiveData()
}