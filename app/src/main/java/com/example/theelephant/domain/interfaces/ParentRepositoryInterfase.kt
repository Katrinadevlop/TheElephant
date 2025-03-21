package com.example.theelephant.domain.interfaces

import com.example.theelephant.data.model.Parent

interface ParentRepositoryInterfase {
    suspend fun saveParent(parent: Parent)

    suspend fun updateParent(parent: Parent, parentId: String): Boolean

    suspend fun getParent(parentId: String, onComplete: (Parent?) -> Unit)

    suspend fun getAllParent(): List<Parent>

    suspend fun changePassword(changePassword:String)
}