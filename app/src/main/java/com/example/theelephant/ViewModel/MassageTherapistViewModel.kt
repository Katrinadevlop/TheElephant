package com.example.theelephant.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.theelephant.Model.DataBase.Entities.MassageTherapist
import com.example.theelephant.Model.DataBase.Repository.MassageTherapistRepository

class MassageTherapistViewModel(massageTherapistRepository: MassageTherapistRepository) : ViewModel()  {
    val massageTherapistData: LiveData<List<MassageTherapist>> = massageTherapistRepository.getMassageTherapist().asLiveData()
}
