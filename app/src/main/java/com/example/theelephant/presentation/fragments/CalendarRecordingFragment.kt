package com.example.theelephant.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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

        transferPhoneProfile()

        var selectedDate = ""
        var specialist = ""

        binding.specialistSpinner.isVisible = selectedDate.isNotEmpty()

        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"
            specialist =  binding.specialistSpinner.selectedItem.toString()

            val calendarRecordingFragment = CalendarRecordingFragment()

            val bundle = Bundle().apply {
                putString("selectedDate", selectedDate)
                putString("specialist", specialist)
            }
            calendarRecordingFragment.arguments = bundle

            binding.specialistSpinner.isVisible = selectedDate.isNotEmpty()

            if (specialist.isEmpty()) return@setOnDateChangeListener

            val recordListFragment = RecordListFragment.newInstance(selectedDate, specialist)
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, recordListFragment).commit()
        }

        lifecycleScope.launch {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, calendarRecordingViewModel.getSpecialist())
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.specialistSpinner.adapter = adapter
        }

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

    //TODO
    fun transferPhoneProfile(){
        val args: CalendarRecordingFragmentArgs by navArgs()
        val action = CalendarRecordingFragmentDirections.actionCalendarRecordingFragmentToPersonalAccountFragment(args.userPhone)
        findNavController().navigate(action)
    }
}
