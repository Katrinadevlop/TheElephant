package com.example.theelephant.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.theelephant.Model.DataBase.Entities.Neuropsychologist
import com.example.theelephant.Model.DataBase.Repository.NeuropsychologistRepository

class NeuropsychologistViewModel(neuropsychologistRepository: NeuropsychologistRepository) : ViewModel()  {
    val neuropsychologistData: LiveData<List<Neuropsychologist>> = neuropsychologistRepository.getNeuropsychologist().asLiveData()
}
