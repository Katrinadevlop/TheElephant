package com.example.theelephant.Model.DataBase.Repository

import com.example.theelephant.Model.DataBase.DataBase
import com.example.theelephant.Model.DataBase.Entities.Neuropsychologist

class NeuropsychologistRepository(private val dataBase: DataBase) {
    private val neuropsychologistDao = dataBase.NeuropsychologistDao()

    suspend fun getNeuropsychologist() {
        neuropsychologistDao.getNeuropsychologist()
    }

    suspend fun setNeuropsychologist(id: Int) {
        neuropsychologistDao.setNeuropsychologist(id)
    }

    suspend fun deleteNeuropsychologist(id: Int) {
        neuropsychologistDao.deleteNeuropsychologist(id)
    }

    suspend fun deleteAllNeuropsychologist() {
        neuropsychologistDao.deleteAllNeuropsychologist()
    }

    suspend fun insertNeuropsychologist(neuropsychologist: Neuropsychologist) {
        neuropsychologistDao.insertNeuropsychologist(neuropsychologist)
    }
}