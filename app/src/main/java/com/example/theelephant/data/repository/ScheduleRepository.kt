package com.example.theelephant.data.repository

import android.util.Log
import com.example.theelephant.data.model.Schedule
import com.example.theelephant.domain.interfaces.ScheduleInterface
import com.google.firebase.database.FirebaseDatabase

private val database =
    FirebaseDatabase.getInstance("https://the-elephant-40f43-default-rtdb.europe-west1.firebasedatabase.app")
private var refSchedule = database.reference.child("schedule")


class ScheduleRepository : ScheduleInterface {
    override suspend fun getAllSchedule(): List<Schedule> {
        TODO("Not yet implemented")
    }

    override suspend fun getScheduleById(scheduleId: Int): Schedule {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSchedule(scheduleId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun changeSchedule(scheduleId: Int) {
        val updateSchedule =
    }

    override suspend fun addSSchedule(schedule: Schedule) {
        val scheduleId = refSchedule.push().key ?: return

        val scheduleData = schedule.copy(id = scheduleId)
        refSchedule.child(scheduleId).setValue(scheduleData).addOnCompleteListener { task ->
            if (task.isSuccessful)
                Log.e("error", "Специалист успешно зарегистрирован")
            else
                Log.e("error", "Ошибка регистрации: ${task.exception?.message}")
        }
    }
}