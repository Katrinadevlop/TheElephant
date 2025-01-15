package com.example.theelephant.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.theelephant.Model.DataBase.Entities.SpeechTherapist
import com.example.theelephant.Model.DataBase.Repository.SpeechTherapistRepository

class SpeechTherapistViewModel(speechTherapistRepository: SpeechTherapistRepository) : ViewModel()  {
    val speechTherapistData: LiveData<List<SpeechTherapist>> = speechTherapistRepository.getSpeechTherapist().asLiveData()
}
