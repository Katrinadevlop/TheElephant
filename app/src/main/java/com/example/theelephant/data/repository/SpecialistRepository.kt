package com.example.theelephant.data.repository

import android.util.Log
import com.example.theelephant.data.model.Parent
import com.example.theelephant.data.model.Specialist
import com.example.theelephant.domain.interfaces.SpecialistRepositoryInterface
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private val database =
    FirebaseDatabase.getInstance("https://the-elephant-40f43-default-rtdb.europe-west1.firebasedatabase.app")
private var refSpecialists = database.reference.child("specialist")

class SpecialistRepository : SpecialistRepositoryInterface {
    override fun saveSpecialist(specialist: Specialist) {
        val specialistId = refSpecialists.push().key ?: return

        val specialistData = mapOf(
            "id" to specialistId,
            "name" to specialist.name,
            "surname" to specialist.surname,
            "phone" to specialist.phone,
            "password" to specialist.password,
            "role" to specialist.role,
            "specialization" to specialist.specialization
        )

        refSpecialists.child(specialistId).setValue(specialistData).addOnCompleteListener { task ->
            if (task.isSuccessful)
                Log.e("error", "Специалист успешно зарегистрирован")
            else
                Log.e("error", "Ошибка регистрации: ${task.exception?.message}")
        }
    }

    override fun changeSpecialist(
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
                onComplete(true)
            }
            .addOnFailureListener {
                onComplete(false)
            }
    }

    override fun getSpecialist(specialistId: String, onComplete: (Specialist?) -> Unit) {
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
                onComplete(specialist)
            } else {
                onComplete(null)
            }
        }.addOnFailureListener {
            onComplete(null)
        }
    }

    override fun getAllSpecialist(onComplete: (List<Specialist>) -> Unit) {
        refSpecialists.get().addOnSuccessListener { snapshot ->
            val listSpecialist = mutableListOf<Specialist>()

            if (snapshot.exists()) {
                for (child in snapshot.children) {
                    val specialist = Specialist(
                        name = child.child("name").value as String,
                        surname = child.child("surname").value as String,
                        phone = child.child("phone").value as String,
                        password = child.child("password").value as String,
                        role = Specialist.Role.valueOf(child.child("role").value as String),
                        specialization = child.child("specialization").value as String
                    )
                    listSpecialist.add(specialist)
                }
            } else {
                Log.e("error", "Ошибка: специалисты не найдены")
            }

            onComplete(listSpecialist)
        }.addOnFailureListener {
            Log.e("error", "Ошибка при получении данных: ${it.message}")
            onComplete(emptyList())
        }
    }

    override fun getParentByPhone(phone: String, callback: (Parent?) -> Unit) {
        val refParent = FirebaseDatabase.getInstance().getReference("parents")

        refParent.orderByChild("phone").equalTo(phone)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (child in snapshot.children) {
                            val parent = child.getValue(Parent::class.java)
                            callback(parent)
                            return
                        }
                    }
                    callback(null)
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(null)
                }
            })
    }
}