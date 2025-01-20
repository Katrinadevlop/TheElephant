package com.example.theelephant.View.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
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

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"

            val calendarRecordingFragment = CalendarRecordingFragment()

            val bundle = Bundle().apply {
                putString("selectedDate", selectedDate)
            }
            calendarRecordingFragment.arguments = bundle

            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, RecordListFragment())
            transaction.commit()
        }
    }
}
