package com.example.theelephant.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.theelephant.Model.DataBase.Entities.Psychologist
import com.example.theelephant.Model.DataBase.Repository.PsychologistRepository

class PsychologistViewModel(psychologistRepository: PsychologistRepository) : ViewModel()  {
    val psychologistData: LiveData<List<Psychologist>> = psychologistRepository.getPsychologist().asLiveData()
}
