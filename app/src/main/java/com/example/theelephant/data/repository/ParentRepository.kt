package com.example.theelephant.data.repository

import android.util.Log
import com.example.theelephant.data.model.Parent
import com.example.theelephant.domain.interfaces.ParentRepositoryInterfase
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

private val database =
    FirebaseDatabase.getInstance("https://the-elephant-40f43-default-rtdb.europe-west1.firebasedatabase.app")
private val refParents = database.reference.child("parents")

class ParentRepository : ParentRepositoryInterfase {
    override suspend fun saveParent(parent: Parent) {
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

    override suspend fun updateParent(parent: Parent, parentId: String): Boolean {
        return suspendCoroutine { continuation ->
            val refParent = refParents.child(parentId)

            val updatedParent = mapOf(
                "name" to parent.name,
                "surname" to parent.surname,
                "phone" to parent.phone,
                "password" to parent.password,
            )

            refParent.updateChildren(updatedParent)
                .addOnSuccessListener { continuation.resume(true) }
                .addOnFailureListener { continuation.resume(false) }
        }
    }

    override suspend fun getParent(phone: String, onComplete: (Parent?) -> Unit) {
        val refParent = refParents.child(phone)

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

    override suspend fun getAllParent(): List<Parent> {
        return try {
            val snapshot = refParents.get().await()
            val listParents = snapshot.children.mapNotNull { parentSnapshot ->
                try {
                    Parent(
                        name = parentSnapshot.child("name").value as String,
                        surname = parentSnapshot.child("surname").value as String,
                        phone = parentSnapshot.child("phone").value as String,
                        password = parentSnapshot.child("password").value as String
                    )
                } catch (e: Exception) {
                    null
                }
            }
            listParents
        } catch (e: Exception) {
            Log.e("error", "Ошибка при получении данных: ${e.message}")
            emptyList()
        }
    }

    override suspend fun changePassword(changePassword:String){
        TODO("Not yet implemented")
    }
}