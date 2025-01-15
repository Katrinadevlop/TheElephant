package com.example.theelephant.Model.DataBase.Repository

import com.example.theelephant.Model.DataBase.DataBase
import com.example.theelephant.Model.DataBase.Entities.Parent

class ParentRepository(private val dataBase: DataBase) {
    private val parentDao = dataBase.ParentDao()

    suspend fun getParent() {
        parentDao.getParent()
    }

    suspend fun setParent(id: Int) {
        parentDao.setParent(id)
    }

    suspend fun deleteParent(id: Int) {
        parentDao.deleteParent(id)
    }

    suspend fun deleteAllParent() {
        parentDao.deleteAllParent()
    }

    suspend fun insertParent(parent: Parent) {
        parentDao.insertParent(parent)
    }
}