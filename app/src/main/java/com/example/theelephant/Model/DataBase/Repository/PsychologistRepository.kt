package com.example.theelephant.Model.DataBase.Repository

import com.example.theelephant.Model.DataBase.DataBase
import com.example.theelephant.Model.DataBase.Entities.Psychologist

class PsychologistRepository(private val dataBase: DataBase) {
    private val psychologistDao = dataBase.PsychologistDao()

    suspend fun getPsychologist() {
        psychologistDao.getPsychologist()
    }

    suspend fun setPsychologist(id: Int) {
        psychologistDao.setPsychologist(id)
    }

    suspend fun deletePsychologist(id: Int) {
        psychologistDao.deletePsychologist(id)
    }

    suspend fun deleteAllPsychologist() {
        psychologistDao.deleteAllPsychologist()
    }

    suspend fun insertPsychologist(psychologist: Psychologist) {
        psychologistDao.insertPsychologist(psychologist)
    }
}