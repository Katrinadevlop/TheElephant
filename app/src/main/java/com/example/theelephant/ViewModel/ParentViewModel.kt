package com.example.theelephant.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.theelephant.Model.DataBase.Entities.Parent
import com.example.theelephant.Model.DataBase.Repository.ParentRepository

class ParentViewModel(parentRepository: ParentRepository) : ViewModel() {
    val parentData: LiveData<List<Parent>> = parentRepository.getParent().asLiveData()
}