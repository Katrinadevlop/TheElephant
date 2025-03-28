package com.example.theelephant.data.repository

import android.util.Log
import com.example.theelephant.data.model.Schedule
import com.example.theelephant.domain.interfaces.ScheduleInterface
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

private val database =
    FirebaseDatabase.getInstance("https://the-elephant-40f43-default-rtdb.europe-west1.firebasedatabase.app")
private var refSchedule = database.reference.child("schedule")


class ScheduleRepository : ScheduleInterface {
    override suspend fun addSSchedule(schedule: Schedule, onComplete: (String?) -> Unit) {
        val scheduleId = refSchedule.push().key ?: return onComplete(null)

        val scheduleData = schedule.copy(id = scheduleId)
        refSchedule.child(scheduleId).setValue(scheduleData).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("success", "Запись успещно сохранена")
                onComplete("Запись успещно сохранена")
            } else {
                Log.e("error", "Ошибка добавления: ${task.exception?.message}")
                onComplete(null)
            }
        }
    }

    override suspend fun changeSchedule(
        scheduleId: String,
        schedule: Schedule,
        onComplete: (Boolean) -> Unit,
    ) {
        val refSchedule = refSchedule.child(scheduleId)

        val updateSchedule = mapOf(
            "date" to schedule.date,
            "time" to schedule.time,
            "specialistId" to schedule.specialistId,
            "parentId" to schedule.parentId
        )

        refSchedule.updateChildren(updateSchedule)
            .addOnSuccessListener {
                Log.d("succes", "Запись успешно изменена")
                onComplete(true)
            }
            .addOnSuccessListener {
                Log.e("error", "Ошибка при изменении записи")
                onComplete(false)
            }
    }

    override suspend fun getAllSchedule(): List<Schedule> {
        return try {
            val snapshot = refSchedule.get().await()
            val listSchedule = snapshot.children.mapNotNull { scheduleSnapshot ->
                try {
                    Schedule(
                        date = scheduleSnapshot.child("date").value as String,
                        time = scheduleSnapshot.child("time").value as String,
                        specialistId = scheduleSnapshot.child("specialistId").value as String,
                        parentId = scheduleSnapshot.child("parentId").value as String,
                    )
                } catch (e: Exception) {
                    Log.e("error", "Ошибка при получении данных: ${e.message}")
                    null
                }
            }
            listSchedule
        } catch (e: Exception) {
            Log.e("error", "Ошибка при получении данных: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getScheduleById(scheduleId: String, onComplete: (Schedule?) -> Unit) {
        val refSchedule = refSchedule.child(scheduleId)

        refSchedule.get().addOnSuccessListener { scheduleSnapshot ->
            if (scheduleSnapshot.exists()) {
                val schedule = Schedule(
                    date = scheduleSnapshot.child("date").value as String,
                    time = scheduleSnapshot.child("time").value as String,
                    specialistId = scheduleSnapshot.child("specialistId").value as String,
                    parentId = scheduleSnapshot.child("parentId").value as String,
                )
                Log.d("success", "Запись успешно получена по id")
                onComplete(schedule)
            } else {
                Log.e("error", "Ошибка получения записи по id")
                onComplete(null)
            }
        }.addOnFailureListener {
            Log.e("error", "Ошибка получения записи по id")
            onComplete(null)
        }
    }

    override suspend fun deleteSchedule(scheduleId: String, onComplete: (String?) -> Unit) {
        try {
            refSchedule.child(scheduleId).removeValue()
            onComplete("Запись удалена")
            Log.d("success", "Запись удалена")
        } catch (e: Exception) {
            Log.e("error", "Ошибка удаления записи")
            onComplete(null)
        }
    }
}