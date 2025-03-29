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
import com.example.theelephant.presentation.viewModel.PersonalAccountViewModel

class PersonalAccountFragment : Fragment() {

    private lateinit var binding: FragmentPersonalAccountBinding
 /*   private val personalAccountViewModel: PersonalAccountViewModel
        get() = PersonalAccountViewModel(PersonalAccountUseCase(ParentRepository()))*/

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

        binding.editTheme.setOnClickListener {
            findNavController().navigate(R.id.action_personalAccountFragment_to_changeThemeBlankFragment)
        }

        binding.exitAccount.setOnClickListener {
        }
    }
}