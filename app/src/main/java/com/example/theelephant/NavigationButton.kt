package com.example.theelephant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.theelephant.databinding.FragmentNavigationButtonBinding

class NavigationButton : Fragment() {

    private lateinit var binding:FragmentNavigationButtonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentNavigationButtonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val controller = findNavController()

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.calendarButtonMenu -> {
                    controller.navigate(R.id.calendarRecording)
                    true
                }
                R.id.profileButtonMenu -> {
                    controller.navigate(R.id.profileButtonMenu)
                    true
                }
                else -> false
            }
        }
    }
}