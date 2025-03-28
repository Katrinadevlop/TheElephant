package com.example.theelephant.domain.interfaces

import com.example.theelephant.data.model.Parent

interface ParentRepositoryInterfase {
    suspend fun addParent(parent: Parent)
    suspend fun changeParent(parent: Parent, parentId: String):Boolean
    suspend fun getAllParent(): List<Parent>
    suspend fun getParentById(parentId: String, onComplete: (Parent?) -> Unit)
    suspend fun changePassword(parentId: String, changePassword: String, onComplete: (Boolean) -> Unit)
}