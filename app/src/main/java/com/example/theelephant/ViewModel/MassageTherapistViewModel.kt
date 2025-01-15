package com.example.theelephant.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.Model.DataBase.Entities.MassageTherapist
import com.example.theelephant.Model.DataBase.Repository.MassageTherapistRepository
import kotlinx.coroutines.launch

class MassageTherapistViewModel(private val massageTherapistRepository: MassageTherapistRepository) : ViewModel()  {
    private val _massageTherapistData = MutableLiveData<List<MassageTherapist>>()
    val massageTherapistData: LiveData<List<MassageTherapist>> get() = _massageTherapistData

    fun fetchMassageTherapist() {
        viewModelScope.launch {
            val massageTherapist = massageTherapistRepository.getMassageTherapist()
            _massageTherapistData.postValue(massageTherapist)
        }
    }
}
