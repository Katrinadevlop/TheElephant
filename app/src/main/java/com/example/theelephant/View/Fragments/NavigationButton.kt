package com.example.theelephant.View.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            val selectedFragment: Fragment = when (item.itemId) {
                R.id.calendarButtonMenu -> CalendarRecording()
                R.id.profileButtonMenu -> PersonalAccount()
                else -> return@setOnNavigationItemSelectedListener false
            }

            childFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayout, selectedFragment)
                .commit()
            true
        }
    }
}