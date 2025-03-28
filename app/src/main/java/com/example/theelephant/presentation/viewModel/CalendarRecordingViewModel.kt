package com.example.theelephant.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.data.model.Schedule
import com.example.theelephant.data.model.Specialist
import com.example.theelephant.domain.ScheduleUseCase
import kotlinx.coroutines.launch

class CalendarRecordingViewModel(private val scheduleUseCase: ScheduleUseCase) : ViewModel() {

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

    suspend fun getSpecialist(): List<String> {
        return try {
            val specialistList = scheduleUseCase.getAllSpecialist()
            val specialistNames = specialistList.map { it.specialization }.toMutableList()
            specialistNames.add(0, "Выберите специалиста")
            specialistNames
        } catch (e: Exception) {
            Log.e("ViewModel", "Ошибка при загрузке специалистов: ${e.message}")
            emptyList()
        }
    }
}