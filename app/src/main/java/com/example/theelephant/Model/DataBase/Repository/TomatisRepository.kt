package com.example.theelephant.Model.DataBase.Repository

import com.example.theelephant.Model.DataBase.DataBase
import com.example.theelephant.Model.DataBase.Entities.Tomatis

class TomatisRepository(private val dataBase: DataBase) {
    private val tomatisDao = dataBase.TomatisDao()

    suspend fun getTomatis() {
        tomatisDao.getTomatis()
    }

    suspend fun setTomatis(id: Int) {
        tomatisDao.setTomatis(id)
    }

    suspend fun deleteTomatis(id: Int) {
        tomatisDao.deleteTomatis(id)
    }

    suspend fun deleteAllTomatis() {
        tomatisDao.deleteAllTomatis()
    }

    suspend fun insertTomatis(tomatis: Tomatis) {
        tomatisDao.insertTomatis(tomatis)
    }
}