package com.example.theelephant.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.Model.DataBase.Entities.Parent
import com.example.theelephant.Model.DataBase.Repository.ParentRepository
import kotlinx.coroutines.launch

class ParentViewModel(private val parentRepository: ParentRepository) : ViewModel() {
    private val _parentData = MutableLiveData<List<Parent>>()
    val parentData: LiveData<List<Parent>> get() = _parentData

    fun fetchParent() {
        viewModelScope.launch {
            val parent = parentRepository.getParent()
            _parentData.postValue(parent)
        }
    }
}