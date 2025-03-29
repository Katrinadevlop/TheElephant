package com.example.theelephant.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.theelephant.data.model.Schedule
import com.example.theelephant.data.repository.ScheduleRepository
import com.example.theelephant.data.repository.SpecialistRepository
import com.example.theelephant.databinding.FragmentRecordListBinding
import com.example.theelephant.domain.ScheduleUseCase
import com.example.theelephant.presentation.viewModel.RecordingListViewModel

class RecordListFragment : Fragment() {

    private lateinit var binding: FragmentRecordListBinding
    private val recordingListViewModel: RecordingListViewModel
        get() = RecordingListViewModel(
            ScheduleUseCase(
                scheduleInterface = ScheduleRepository(),
                specialistRepositoryInterface = SpecialistRepository()
            )
        )

    companion object {
        fun newInstance(selectedDate: String, specialist: String): RecordListFragment {
            return RecordListFragment().apply {
                arguments = Bundle().apply {
                    putString("selectedDate", selectedDate)
                    putString("specialist", specialist)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRecordListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedDate = arguments?.getString("selectedDate")
        val specialist = arguments?.getString("specialist")

        binding.buttonTime900.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage(
                "\nЗапись к специалисту $specialist\n" +
                        "Дата: $selectedDate\n" +
                        "Время: ${binding.buttonTime900.text}"
            )
                .setPositiveButton("Записаться") { _, _ ->
                    val record = Schedule(
                        id = "",
                        selectedDate.toString(),
                        binding.buttonTime900.text.toString(),
                        specialist.toString(),
                        parentId = ""
                    )
                    recordingListViewModel.saveSchedule(schedule = record,
                        onSuccess = {
                            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                        },
                        onError = { errorMessage ->
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
                        }
                    )
                }
                .setNegativeButton("Отмена") { dialog, _ ->
                    dialog.dismiss()
                }
            builder.create().show()
        }

    }
}
