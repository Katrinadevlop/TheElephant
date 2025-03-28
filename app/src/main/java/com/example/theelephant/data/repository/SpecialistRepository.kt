package com.example.theelephant.data.repository

import android.util.Log
import com.example.theelephant.data.model.Specialist
import com.example.theelephant.domain.interfaces.SpecialistRepositoryInterface
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

private val database =
    FirebaseDatabase.getInstance("https://the-elephant-40f43-default-rtdb.europe-west1.firebasedatabase.app")
private var refSpecialists = database.reference.child("specialist")

class SpecialistRepository : SpecialistRepositoryInterface {
    override suspend fun addSpecialist(specialist: Specialist, onComplete: (String?) -> Unit) {
        val specialistId = refSpecialists.push().key ?: return onComplete(null)

        val specialistWithId = specialist.copy(id = specialistId)

        refSpecialists.child(specialistId).setValue(specialistWithId)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("success", "Специалист успешно зарегистрирован")
                    onComplete("Специалист успешно зарегистрирован")
                } else {
                    Log.e("error", "Ошибка регистрации: ${task.exception?.message}")
                    onComplete(null)
                }
            }
    }

    override suspend fun changeSpecialist(
        specialist: Specialist,
        specialistId: String,
        onComplete: (Boolean) -> Unit,
    ) {
        val refSpecialist = refSpecialists.child(specialistId)

        val updatedSpecialist = mapOf(
            "name" to specialist.name,
            "surname" to specialist.surname,
            "phone" to specialist.phone,
            "password" to specialist.password,
            "role" to specialist.role.name,
            "specialization" to specialist.specialization
        )

        refSpecialist.updateChildren(updatedSpecialist)
            .addOnSuccessListener {
                Log.d("success", "Специалист успешно изменен")
                onComplete(true)
            }
            .addOnFailureListener {
                Log.e("error", "Ошибка при изменении специалиста")
                onComplete(false)
            }
    }

    override suspend fun getAllSpecialist(): List<Specialist> {
        return try {
            val snapshot = refSpecialists.get().await()
            val listSpecialist = snapshot.children.mapNotNull { specialistSnapshot ->
                try {
                    Specialist(
                        name = specialistSnapshot.child("name").value as String,
                        surname = specialistSnapshot.child("surname").value as String,
                        phone = specialistSnapshot.child("phone").value as String,
                        password = specialistSnapshot.child("password").value as String,
                        role = Specialist.Role.valueOf(specialistSnapshot.child("role").value as String),
                        specialization = specialistSnapshot.child("specialization").value as String
                    )
                } catch (e: Exception) {
                    Log.e("error", "Ошибка при получении данных: ${e.message}")
                    null
                }
            }
            listSpecialist
        } catch (e: Exception) {
            Log.e("error", "Ошибка при получении данных: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getSpecialistById(
        specialistId: String,
        onComplete: (Specialist?) -> Unit,
    ) {
        val refSpecialist = refSpecialists.child(specialistId)

        refSpecialist.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val specialist = Specialist(
                    name = snapshot.child("name").value as String,
                    surname = snapshot.child("surname").value as String,
                    phone = snapshot.child("phone").value as String,
                    password = snapshot.child("password").value as String,
                    role = snapshot.child("role").value as Specialist.Role,
                    specialization = snapshot.child("specialization").value as String
                )
                Log.d("success", "Специалист успешно получен по id")
                onComplete(specialist)
            } else {
                Log.e("error", "Ошибка получения специалиста по id")
                onComplete(null)
            }
        }.addOnFailureListener {
            Log.e("error", "Ошибка получения специалиста по id")
            onComplete(null)
        }
    }
}