package com.example.theelephant.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.theelephant.R
import com.example.theelephant.data.repository.ScheduleRepository
import com.example.theelephant.data.repository.SpecialistRepository
import com.example.theelephant.databinding.FragmentCalendarRecordingBinding
import com.example.theelephant.domain.ScheduleUseCase
import com.example.theelephant.presentation.viewModel.CalendarRecordingViewModel
import kotlinx.coroutines.launch

class CalendarRecordingFragment : Fragment() {

    private lateinit var binding: FragmentCalendarRecordingBinding
    private val calendarRecordingViewModel: CalendarRecordingViewModel
        get() = CalendarRecordingViewModel(
            ScheduleUseCase(
                scheduleInterface = ScheduleRepository(),
                specialistRepositoryInterface = SpecialistRepository()
            )
        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCalendarRecordingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var selectedDate = ""
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"

            val calendarRecordingFragment = CalendarRecordingFragment()

            val bundle = Bundle().apply {
                putString("selectedDate", selectedDate)
            }
            calendarRecordingFragment.arguments = bundle

            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, RecordListFragment())
            transaction.commit()
        }

        lifecycleScope.launch {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, calendarRecordingViewModel.getSpecialist())
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.specialistSpinner.adapter = adapter
        }

        binding.specialistSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                Log.d("SpinnerSelection", "Выбран специалист: $")//TODO
            }

            override fun onNothingSelected(parent: AdapterView<*>?){}
        }

        //TODO:доделать запись к специалисту
        //val schedule = Schedule(selectedDate, "9:00", specialistId)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            showExitDialog()
        }
    }

    private fun showExitDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Выйти из приложения?").setCancelable(false)
            .setPositiveButton("Да") { _, _ ->
                requireActivity().finish()
            }.setNegativeButton("Нет") { dialog, _ ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
}
