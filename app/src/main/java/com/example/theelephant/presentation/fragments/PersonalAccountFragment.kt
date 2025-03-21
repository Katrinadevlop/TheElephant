package com.example.theelephant.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.theelephant.R
import com.example.theelephant.data.repository.ParentRepository
import com.example.theelephant.databinding.FragmentPersonalAccountBinding
import com.example.theelephant.domain.PersonalAccountUseCase
import com.example.theelephant.presentation.viewModel.PersonalAccountViewModel

class PersonalAccountFragment : Fragment() {

    private lateinit var binding: FragmentPersonalAccountBinding
    private val personalAccountViewModel: PersonalAccountViewModel
        get() = PersonalAccountViewModel(PersonalAccountUseCase(ParentRepository()))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPersonalAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO сделать стили в layout
        //TODO доделать перехват данных
        val userPhone = arguments?.getString("userPhone")
        // binding.phoneTextView.text = userPhone

        binding.editProfile.setOnClickListener {
           findNavController().navigate(R.id.action_personalAccountFragment_to_editProfileBlankFragment)
        }

        binding.editPassword.setOnClickListener {
            findNavController().navigate(R.id.action_personalAccountFragment_to_changePasswordBlankFragment)
        }

      /*  var isDark = getSaveThemeState()
        if (isDark) {
            setDarkTheme()
        } else {
            setLightTheme()
        }*/

        binding.editTheme.setOnClickListener {
            findNavController().navigate(R.id.action_personalAccountFragment_to_changeThemeBlankFragment)

            /*isDark = !isDark
            if (isDark) {
                setDarkTheme()
            } else {
                setLightTheme()
            }
            saveThemeState(isDark)*/
        }

        binding.exitAccount.setOnClickListener {
        }
    }

  /*  fun setDarkTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    fun setLightTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    fun getSaveThemeState(): Boolean {
        val sharedPreferences =
            requireContext().getSharedPreferences("themeState", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isDark", false)
    }

    fun saveThemeState(isDarkTheme: Boolean) {
        val sharedPreferences =
            requireContext().getSharedPreferences("themeState", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit().putBoolean("isDark", isDarkTheme).apply()
    }

    override fun onResume() {
        super.onResume()
        val textColor = if (getSaveThemeState()) R.color.black else R.color.white
        binding.nameTextView.setTextColor(ContextCompat.getColor(requireContext(), textColor))
        binding.surnameTextView.setTextColor(ContextCompat.getColor(requireContext(), textColor))
        binding.phoneTextView.setTextColor(ContextCompat.getColor(requireContext(), textColor))
        binding.remainingClasses.setTextColor(ContextCompat.getColor(requireContext(), textColor))
        binding.editProfile.setTextColor(ContextCompat.getColor(requireContext(), textColor))
        binding.editPassword.setTextColor(ContextCompat.getColor(requireContext(), textColor))
        binding.editTheme.setTextColor(ContextCompat.getColor(requireContext(), textColor))
        binding.exitAccount.setTextColor(ContextCompat.getColor(requireContext(), textColor))
    }*/
}