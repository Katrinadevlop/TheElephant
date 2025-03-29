package com.example.theelephant.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelephant.data.model.Schedule
import com.example.theelephant.data.model.Specialist
import com.example.theelephant.domain.ScheduleUseCase
import kotlinx.coroutines.launch

class CalendarRecordingViewModel(private val scheduleUseCase: ScheduleUseCase) : ViewModel() {
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