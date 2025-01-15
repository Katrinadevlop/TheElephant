package com.example.theelephant.Model.DataBase.Repository

import com.example.theelephant.Model.DataBase.DataBase
import com.example.theelephant.Model.DataBase.Entities.MassageTherapist
import kotlinx.coroutines.flow.Flow

class MassageTherapistRepository(dataBase: DataBase) {
    private val massageTherapistDao = dataBase.MassageTherapistDao()

    fun getMassageTherapist(): Flow<List<MassageTherapist>> {
       return massageTherapistDao.getMassageTherapist()
    }

    fun setMassageTherapist(id: Int) {
        massageTherapistDao.setMassageTherapist(id)
    }

    fun deleteMassageTherapist(id: Int){
        massageTherapistDao.deleteMassageTherapist(id)
    }

    fun deleteAllMassageTherapist(){
        massageTherapistDao.deleteAllMassageTherapist()
    }

    fun insertMassageTherapist(massageTherapist: MassageTherapist){
        massageTherapistDao.insertMassageTherapist(massageTherapist)
    }
}
