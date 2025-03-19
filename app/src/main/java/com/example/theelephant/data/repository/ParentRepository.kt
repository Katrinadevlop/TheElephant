package com.example.theelephant.data.repository

import android.util.Log
import com.example.theelephant.data.model.Parent
import com.example.theelephant.domain.interfaces.ParentRepositoryInterfase
import com.google.firebase.database.FirebaseDatabase

private val database =
    FirebaseDatabase.getInstance("https://the-elephant-40f43-default-rtdb.europe-west1.firebasedatabase.app")
private val refParents = database.reference.child("parents")

class ParentRepository : ParentRepositoryInterfase {
    override fun saveParent(parent: Parent) {
        val parentId = refParents.push().key ?: return

        val parentData = mapOf(
            "id" to parentId,
            "name" to parent.name,
            "surname" to parent.surname,
            "phone" to parent.phone,
            "password" to parent.password
        )

        refParents.child(parentId).setValue(parentData).addOnCompleteListener { task ->
            if (task.isSuccessful)
                Log.e("error", "Родитель успешно зарегистрирован")
            else
                Log.e("error", "Ошибка регистрации: ${task.exception?.message}")
        }
    }

    override fun changeParent(parent: Parent, parentId: String, onComplete: (Boolean) -> Unit) {
        val refParent = refParents.child(parentId)

        val updatedParent = mapOf(
            "name" to parent.name,
            "surname" to parent.surname,
            "phone" to parent.phone,
            "password" to parent.password,
        )

        refParent.updateChildren(updatedParent)
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener {
                onComplete(false)
            }
    }

    override fun getParent(parentId: String, onComplete: (Parent?) -> Unit) {
        val refParent = refParents.child(parentId)

        refParent.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val parent = Parent(
                    name = snapshot.child("name").value as String,
                    surname = snapshot.child("surname").value as String,
                    phone = snapshot.child("phone").value as String,
                    password = snapshot.child("password").value as String,
                )
                onComplete(parent)
            } else {
                onComplete(null)
            }
        }.addOnFailureListener {
            onComplete(null)
        }
    }

    override fun getAllParent(onComplete: (List<Parent>) -> Unit) {
        refParents.get().addOnSuccessListener { snapshot ->
            val listParents = mutableListOf<Parent>()

            if (snapshot.exists()) {
                for (parentSnapshot in snapshot.children) {
                    val parent = Parent(
                        name = parentSnapshot.child("name").value as String,
                        surname = parentSnapshot.child("surname").value as String,
                        phone = parentSnapshot.child("phone").value as String,
                        password = parentSnapshot.child("password").value as String,
                    )
                    listParents.add(parent)
                }
            } else {
                Log.e("error", "Ошибка: родители не найдены")
            }
            onComplete(listParents)
        }.addOnFailureListener {
            Log.e("error", "Ошибка при получении данных: ${it.message}")
            onComplete(emptyList())
        }
    }

    override fun changePassword(changePassword:String){

    }
}