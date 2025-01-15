package com.example.theelephant.Model.DataBase.Repository

import com.example.theelephant.Model.DataBase.DataBase
import com.example.theelephant.Model.DataBase.Entities.MassageTherapist

class MassageTherapistRepository(private val dataBase: DataBase) {
    private val massageTherapistDao = dataBase.MassageTherapistDao()

    suspend fun getMassageTherapist(){
        massageTherapistDao.getMassageTherapist()
    }

    suspend fun setMassageTherapist(id: Int) {
        massageTherapistDao.setMassageTherapist(id)
    }

    suspend fun deleteMassageTherapist(id: Int){
        massageTherapistDao.deleteMassageTherapist(id)
    }

    suspend fun deleteAllMassageTherapist(){
        massageTherapistDao.deleteAllMassageTherapist()
    }

    suspend fun insertMassageTherapist(massageTherapist: MassageTherapist){
        massageTherapistDao.insertMassageTherapist(massageTherapist)
    }
}
