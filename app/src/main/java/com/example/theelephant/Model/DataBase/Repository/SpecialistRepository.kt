package com.example.theelephant.Model.DataBase.Repository

import com.example.theelephant.Model.DataBase.DataBase
import com.example.theelephant.Model.DataBase.Entities.SpecialistEntity
import kotlinx.coroutines.flow.Flow

class SpecialistRepository(dataBase: DataBase) {
    private val specialistDao = dataBase.SpecialistDao()

    fun getSpecialist(): Flow<List<SpecialistEntity>> {
        return specialistDao.getSpecialist()
    }

    suspend fun setSpecialist(id: Int) {
        specialistDao.setSpecialist(id)
    }

    suspend fun deleteSpecialist(id: Int) {
        specialistDao.deleteSpecialist(id)
    }

    suspend fun deleteAllSpecialist() {
        specialistDao.deleteAllSpecialist()
    }

    suspend fun insertSpecialist(specialist: SpecialistEntity) {
        specialistDao.insertSpecialist(specialist)
    }
}