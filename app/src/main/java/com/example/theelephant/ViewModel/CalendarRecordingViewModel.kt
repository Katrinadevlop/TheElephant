package com.example.theelephant.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.Model.DataBase.Entities.ScheduleEntity
import com.example.theelephant.Model.DataBase.Repository.ScheduleRepository
import com.example.theelephant.Model.Schedule
import kotlinx.coroutines.launch

class CalendarRecordingViewModel(private val scheduleRepository: ScheduleRepository) : ViewModel() {

    fun saveSchedule(schedule: Schedule, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val date = schedule.date
        val time = schedule.time
        val specialist = schedule.specialistId

        //TODO првоерки на уже записанные окна
        if (date.isBlank() || time.isBlank()) {
            return onError("Выберите все данные корректно")
        }

        val scheduleEntity = ScheduleEntity(
            date = schedule.date,
            time = schedule.time,
            specialistId = schedule.specialistId
        )

        viewModelScope.launch {
            try {
                scheduleRepository.insertSchedule(scheduleEntity)
                onSuccess()
            } catch (e: Exception) {
                onError("Ошибка при сохранении данных: ${e.message}")
            }
        }
    }
}