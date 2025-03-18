package com.example.theelephant.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.data.model.Schedule
import kotlinx.coroutines.launch

class CalendarRecordingViewModel() : ViewModel() {//private val scheduleRepository: ScheduleRepository

    fun saveSchedule(schedule: Schedule, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val date = schedule.date
        val time = schedule.time
        val specialist = schedule.specialistId

        //TODO првоерки на уже записанные окна
        if (date.isBlank() || time.isBlank()) {
            return onError("Выберите все данные корректно")
        }

       /* val scheduleEntity = ScheduleEntity(
            date = schedule.date,
            time = schedule.time,
            specialistId = schedule.specialistId
        )
*/
        viewModelScope.launch {
           /* try {
                scheduleRepository.insertSchedule(scheduleEntity)
                onSuccess()
            } catch (e: Exception) {
                onError("Ошибка при сохранении данных: ${e.message}")
            }*/
        }
    }
}