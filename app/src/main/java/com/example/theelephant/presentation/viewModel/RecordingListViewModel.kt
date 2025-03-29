package com.example.theelephant.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.data.model.Schedule
import com.example.theelephant.domain.ScheduleUseCase
import kotlinx.coroutines.launch

class RecordingListViewModel(private val scheduleUseCase: ScheduleUseCase) : ViewModel() {
    fun saveSchedule(schedule: Schedule, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                scheduleUseCase.saveRecord(schedule)
                onSuccess("Запись сохранена")
            } catch (e: Exception) {
                onError("Ошибка при сохранении данных: ${e.message}")
            }
        }
    }
}