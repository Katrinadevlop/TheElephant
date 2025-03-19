package com.example.theelephant.domain.interfaces

import com.example.theelephant.data.model.Parent

interface ParentRepositoryInterfase {
    fun saveParent(parent: Parent)

    fun changeParent(parent: Parent, parentId: String, onComplete: (Boolean) -> Unit)

    fun getParent(parentId: String, onComplete: (Parent?) -> Unit)

    fun getAllParent(onComplete: (List<Parent>) -> Unit)

    fun changePassword(changePassword:String)
}