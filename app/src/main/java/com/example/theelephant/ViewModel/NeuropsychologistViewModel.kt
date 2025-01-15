package com.example.theelephant.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.Model.DataBase.Entities.Neuropsychologist
import com.example.theelephant.Model.DataBase.Repository.NeuropsychologistRepository
import kotlinx.coroutines.launch

class NeuropsychologistViewModel(private val neuropsychologistRepository: NeuropsychologistRepository) : ViewModel()  {
    private val _neuropsychologistData = MutableLiveData<List<Neuropsychologist>>()
    val neuropsychologistData: LiveData<List<Neuropsychologist>> get() = _neuropsychologistData

    fun fetchNeuropsychologist() {
        viewModelScope.launch {
            val neuropsychologist = neuropsychologistRepository.getNeuropsychologist()
            _neuropsychologistData.postValue(neuropsychologist)
        }
    }
}
