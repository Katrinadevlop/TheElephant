package com.example.theelephant.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.example.theelephant.R
import com.example.theelephant.databinding.FragmentChangeThemeBlankBinding

class ChangeThemeBlankFragment : Fragment() {

    private lateinit var binding:FragmentChangeThemeBlankBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentChangeThemeBlankBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageButtonBack.setOnClickListener{
            findNavController().navigate(R.id.action_changeThemeBlankFragment_to_personalAccountFragment)
        }

        binding.buttonLightTheme.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        binding.buttonDarkTheme.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}