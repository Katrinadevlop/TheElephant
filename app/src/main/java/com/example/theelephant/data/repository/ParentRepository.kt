package com.example.theelephant.data.repository

import android.util.Log
import com.example.theelephant.data.model.Parent
import com.example.theelephant.domain.interfaces.ParentRepositoryInterfase
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

private val database =
    FirebaseDatabase.getInstance("https://the-elephant-40f43-default-rtdb.europe-west1.firebasedatabase.app")
private val refParents = database.reference.child("parents")

class ParentRepository : ParentRepositoryInterfase {
    override suspend fun addParent(parent: Parent) {
        val parentId = refParents.push().key ?: return

        val parentWithId = parent.copy(id = parentId)

        refParents.child(parentId).setValue(parentWithId).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("success", "Родитель успешно зарегистрирован")
            } else {
                Log.e("error", "Ошибка регистрации: ${task.exception?.message}")
            }
        }
    }

    override suspend fun changeParent(
        parent: Parent,
        parentId: String
    ): Boolean {
        return try {
            val refParent = refParents.child(parentId)

            val updatedParent = mapOf(
                "name" to parent.name,
                "surname" to parent.surname,
                "phone" to parent.phone,
                "password" to parent.password
            )

            refParent.updateChildren(updatedParent).await()
            Log.d("success", "Родитель успешно изменен")
            true
        } catch (e: Exception) {
            Log.e("error", "Ошибка. Родитель не изменен: ${e.message}")
            false
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
                    Log.e("error", "Ошибка при получении данных: ${e.message}")
                    null
                }
            }
            listParents
        } catch (e: Exception) {
            Log.e("error", "Ошибка при получении данных: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getParentById(
        parentId: String,
        onComplete: (Parent?) -> Unit,
    ) {
        val refParent = refParents.child(parentId)

        refParent.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val parent = Parent(
                    name = snapshot.child("name").value as String,
                    surname = snapshot.child("surname").value as String,
                    phone = snapshot.child("phone").value as String,
                    password = snapshot.child("password").value as String,
                )
                Log.d("success", "Успешно, родитель получен по id")
                onComplete(parent)
            } else {
                Log.e("error", "Ошибка при получении данных родителя по id")
                onComplete(null)
            }
        }.addOnFailureListener {
            Log.e("error", "Ошибка при получении данных родителя по id")
            onComplete(null)
        }
    }

    override suspend fun changePassword(
        parentId: String,
        changePassword: String,
        onComplete: (Boolean) -> Unit,
    ) {
        val refParent = refParents.child(parentId)

        val updatedParent = mapOf(
            "password" to changePassword,
        )

        refParent.updateChildren(updatedParent)
            .addOnSuccessListener {
                Log.d("success", "Пароль успешно изменен")
                onComplete(true)
            }
            .addOnFailureListener {
                Log.e("error", "Ошибка. Пароль не изменен")
                onComplete(false)
            }
    }
}