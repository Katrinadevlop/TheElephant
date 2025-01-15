package com.example.theelephant.Model.DataBase.Repository

import com.example.theelephant.Model.DataBase.DataBase
import com.example.theelephant.Model.DataBase.Entities.Neuropsychologist
import kotlinx.coroutines.flow.Flow

class NeuropsychologistRepository(dataBase: DataBase) {
    private val neuropsychologistDao = dataBase.NeuropsychologistDao()

    fun getNeuropsychologist(): Flow<List<Neuropsychologist>> {
        return neuropsychologistDao.getNeuropsychologist()
    }

    fun setNeuropsychologist(id: Int) {
        neuropsychologistDao.setNeuropsychologist(id)
    }

    fun deleteNeuropsychologist(id: Int) {
        neuropsychologistDao.deleteNeuropsychologist(id)
    }

    fun deleteAllNeuropsychologist() {
        neuropsychologistDao.deleteAllNeuropsychologist()
    }

    fun insertNeuropsychologist(neuropsychologist: Neuropsychologist) {
        neuropsychologistDao.insertNeuropsychologist(neuropsychologist)
    }
}