package com.example.theelephant.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.Model.DataBase.Entities.Psychologist
import com.example.theelephant.Model.DataBase.Repository.PsychologistRepository
import kotlinx.coroutines.launch

class PsychologistViewModel(private val psychologistRepository: PsychologistRepository) : ViewModel()  {
    private val _psychologistData = MutableLiveData<List<Psychologist>>()
    val psychologistData: LiveData<List<Psychologist>> get() = _psychologistData

    fun fetchPsychologist() {
        viewModelScope.launch {
            val psychologist = psychologistRepository.getPsychologist()
            _psychologistData.postValue(psychologist)
        }
    }
}
