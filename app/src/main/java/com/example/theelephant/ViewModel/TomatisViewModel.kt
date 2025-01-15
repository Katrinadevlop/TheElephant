package com.example.theelephant.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.Model.DataBase.Entities.Tomatis
import com.example.theelephant.Model.DataBase.Repository.TomatisRepository
import kotlinx.coroutines.launch

class TomatisViewModel(private val tomatisRepository: TomatisRepository) : ViewModel()  {
    private val _tomatisData = MutableLiveData<List<Tomatis>>()
    val tomatisData: LiveData<List<Tomatis>> get() = _tomatisData

    fun fetchTomatis() {
        viewModelScope.launch {
            val tomatis = tomatisRepository.getTomatis()
            _tomatisData.postValue(tomatis)
        }
    }
}
