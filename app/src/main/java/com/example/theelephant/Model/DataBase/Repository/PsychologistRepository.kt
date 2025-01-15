package com.example.theelephant.Model.DataBase.Repository

import com.example.theelephant.Model.DataBase.DataBase
import com.example.theelephant.Model.DataBase.Entities.Psychologist
import kotlinx.coroutines.flow.Flow

class PsychologistRepository(dataBase: DataBase) {
    private val psychologistDao = dataBase.PsychologistDao()

     fun getPsychologist(): Flow<List<Psychologist>> {
        return psychologistDao.getPsychologist()
    }

     fun setPsychologist(id: Int) {
        psychologistDao.setPsychologist(id)
    }

     fun deletePsychologist(id: Int) {
        psychologistDao.deletePsychologist(id)
    }

     fun deleteAllPsychologist() {
        psychologistDao.deleteAllPsychologist()
    }

     fun insertPsychologist(psychologist: Psychologist) {
        psychologistDao.insertPsychologist(psychologist)
    }
}