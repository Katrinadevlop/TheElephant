package com.example.theelephant.Model.DataBase.Repository

import com.example.theelephant.Model.DataBase.DataBase
import com.example.theelephant.Model.DataBase.Entities.ParentEntity
import com.example.theelephant.Model.Parent
import kotlinx.coroutines.flow.Flow

class ParentRepository(dataBase: DataBase) {
    private val parentDao = dataBase.ParentDao()

    fun getParent(): Flow<List<ParentEntity>> {
        return parentDao.getParent()
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

    suspend fun insertParent(parent: ParentEntity) {
        parentDao.insertParent(parent)
    }
}