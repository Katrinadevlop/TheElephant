package com.example.theelephant.Model.DataBase.Repository

import com.example.theelephant.Model.DataBase.DataBase
import com.example.theelephant.Model.DataBase.Entities.Тeurodefectologist

class TeurodefectologistRepository(private val dataBase:DataBase) {
    private val teurodefectologistDao = dataBase.ТeurodefectologistDao()

    suspend fun getТeurodefectologist(){
        teurodefectologistDao.getТeurodefectologist()
    }

    suspend fun setТeurodefectologist(id: Int){
        teurodefectologistDao.setТeurodefectologist(id)
    }

    suspend fun deleteТeurodefectologist(id:Int){
        teurodefectologistDao.deleteТeurodefectologist(id)
    }

    suspend fun deleteAllТeurodefectologist(){
        teurodefectologistDao.deleteAllТeurodefectologist()
    }

    suspend fun insertТeurodefectologist(teurodefectologist: Тeurodefectologist){
        teurodefectologistDao.insertТeurodefectologist(teurodefectologist)
    }
}