package com.example.theelephant.Model.DataBase.Repository

import com.example.theelephant.Model.DataBase.DataBase
import com.example.theelephant.Model.DataBase.Entities.Tomatis
import kotlinx.coroutines.flow.Flow

class TomatisRepository(dataBase: DataBase) {
    private val tomatisDao = dataBase.TomatisDao()

    fun getTomatis(): Flow<List<Tomatis>> {
        return tomatisDao.getTomatis()
    }

    fun setTomatis(id: Int) {
        tomatisDao.setTomatis(id)
    }

    fun deleteTomatis(id: Int) {
        tomatisDao.deleteTomatis(id)
    }

    fun deleteAllTomatis() {
        tomatisDao.deleteAllTomatis()
    }

    fun insertTomatis(tomatis: Tomatis) {
        tomatisDao.insertTomatis(tomatis)
    }
}