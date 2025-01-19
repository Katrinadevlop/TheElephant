package com.example.theelephant.View.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.theelephant.R
import com.example.theelephant.databinding.FragmentNavigationButtonBinding

class NavigationButton : Fragment() {

    private lateinit var binding: FragmentNavigationButtonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNavigationButtonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.calendarButtonMenu -> {
                    binding.frameLayout.findNavController().navigate(R.id.calendarRecording)
                    //findNavController().navigate(R.id.calendarRecording)
                    true
                }

                R.id.profileButtonMenu -> {
                    findNavController().navigate(R.id.personalAccount)
                    true
                }

                else -> false
            }
        }
    }
}