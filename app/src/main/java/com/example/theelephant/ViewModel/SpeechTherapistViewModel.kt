package com.example.theelephant.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.Model.DataBase.Entities.SpeechTherapist
import com.example.theelephant.Model.DataBase.Repository.SpeechTherapistRepository
import kotlinx.coroutines.launch

class SpeechTherapistViewModel(private val speechTherapistRepository: SpeechTherapistRepository) : ViewModel()  {
    private val _speechTherapistData = MutableLiveData<List<SpeechTherapist>>()
    val speechTherapistData: LiveData<List<SpeechTherapist>> get() = _speechTherapistData

    fun fetchSpeechTherapist() {
        viewModelScope.launch {
            val speechTherapist = speechTherapistRepository.getSpeechTherapist()
            _speechTherapistData.postValue(speechTherapist)
        }
    }
}
