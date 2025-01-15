package com.example.theelephant.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.theelephant.Model.DataBase.Entities.Tomatis
import com.example.theelephant.Model.DataBase.Repository.TomatisRepository

class TomatisViewModel(tomatisRepository: TomatisRepository) : ViewModel()  {
    val tomatisData: LiveData<List<Tomatis>> = tomatisRepository.getTomatis().asLiveData()
}
