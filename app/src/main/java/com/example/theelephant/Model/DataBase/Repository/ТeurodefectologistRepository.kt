package com.example.theelephant.Model.DataBase.Repository

import com.example.theelephant.Model.DataBase.DataBase
import com.example.theelephant.Model.DataBase.Entities.Тeurodefectologist
import kotlinx.coroutines.flow.Flow

class ТeurodefectologistRepository(dataBase:DataBase) {
    private val teurodefectologistDao = dataBase.ТeurodefectologistDao()

     fun getТeurodefectologist(): Flow<List<Тeurodefectologist>> {
        return teurodefectologistDao.getТeurodefectologist()
    }

     fun setТeurodefectologist(id: Int){
        teurodefectologistDao.setТeurodefectologist(id)
    }

     fun deleteТeurodefectologist(id:Int){
        teurodefectologistDao.deleteТeurodefectologist(id)
    }

     fun deleteAllТeurodefectologist(){
        teurodefectologistDao.deleteAllТeurodefectologist()
    }

     fun insertТeurodefectologist(teurodefectologist: Тeurodefectologist){
        teurodefectologistDao.insertТeurodefectologist(teurodefectologist)
    }
}