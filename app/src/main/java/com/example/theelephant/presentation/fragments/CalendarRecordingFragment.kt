package com.example.theelephant.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.theelephant.R
import com.example.theelephant.databinding.FragmentCalendarRecordingBinding

class CalendarRecordingFragment : Fragment() {

    private lateinit var binding: FragmentCalendarRecordingBinding

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
        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
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

       /* val specialistExemplar = SpecialistProvider()
        val specialist = specialistExemplar.getSpecialist().map {it.specialization}.toMutableList()
        specialist.add(0, "Выберите специалиста")*/

     /*   val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, specialist)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.specialistSpinner.adapter = adapter*/
/*

        var selectedSpecialist = ""
        binding.specialistSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedSpecialist = specialist[position]
                Log.d("SpinnerSelection", "Выбран специалист: $selectedSpecialist")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val id = specialistExemplar.getSpecialist().find{selectedSpecialist == it.specialization}
        val specialistId = id
*/


        //TODO:доделать запись к специалисту
        //val schedule = Schedule(selectedDate, "9:00", specialistId)


    }
}
