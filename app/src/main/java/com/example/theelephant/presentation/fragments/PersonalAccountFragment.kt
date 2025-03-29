package com.example.theelephant.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.theelephant.R
import com.example.theelephant.data.model.Parent
import com.example.theelephant.data.repository.ParentRepository
import com.example.theelephant.data.repository.SpecialistRepository
import com.example.theelephant.databinding.FragmentPersonalAccountBinding
import com.example.theelephant.domain.PersonalAccountUseCase
import com.example.theelephant.presentation.viewModel.PersonalAccountViewModel

class PersonalAccountFragment : Fragment() {

    private lateinit var binding: FragmentPersonalAccountBinding
    private val personalAccountViewModel: PersonalAccountViewModel
        get() = PersonalAccountViewModel(
            PersonalAccountUseCase(
                parentRepositoryInterfase = ParentRepository(),
                specialistRepositoryInterface = SpecialistRepository()
            )
        )

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
        val args: PersonalAccountFragmentArgs by navArgs()
        binding.phoneTextView.text = args.userPhone
        personalAccountViewModel.getUser(
            args.userPhone,
            onSuccessParent = { currentParent ->
                binding.nameTextView.text = currentParent.name
                binding.surnameTextView.text = currentParent.surname
            },
            onSuccessSpecialist = { currentSpecialist ->
                binding.nameTextView.text = currentSpecialist.name
                binding.surnameTextView.text = currentSpecialist.surname
            },
            onError = { errorMessage ->
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
            }
        )

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
            findNavController().navigate(R.id.action_personalAccountFragment_to_userRegistration)
        }
    }
}