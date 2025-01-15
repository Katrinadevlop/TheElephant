package com.example.theelephant.Model.DataBase.Repository

import com.example.theelephant.Model.DataBase.DataBase
import com.example.theelephant.Model.DataBase.Entities.Parent
import kotlinx.coroutines.flow.Flow

class ParentRepository(dataBase: DataBase) {
    private val parentDao = dataBase.ParentDao()

    fun getParent(): Flow<List<Parent>> {
        return parentDao.getParent()
    }

    fun setParent(id: Int) {
        parentDao.setParent(id)
    }

    fun deleteParent(id: Int) {
        parentDao.deleteParent(id)
    }

    fun deleteAllParent() {
        parentDao.deleteAllParent()
    }

    fun insertParent(parent: Parent) {
        parentDao.insertParent(parent)
    }
}