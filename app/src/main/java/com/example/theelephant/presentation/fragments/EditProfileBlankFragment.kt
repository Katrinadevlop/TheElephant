package com.example.theelephant.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.theelephant.R
import com.example.theelephant.data.model.Parent
import com.example.theelephant.data.repository.ParentRepository
import com.example.theelephant.databinding.FragmentEditProfileBlankBinding
import com.example.theelephant.domain.EditProfileUseCase
import com.example.theelephant.presentation.viewModel.EditProfileViewModel

class EditProfileBlankFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBlankBinding
    private val editProfileViewModel: EditProfileViewModel
        get() = EditProfileViewModel(
            EditProfileUseCase(ParentRepository())
        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEditProfileBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView.setOnClickListener {

        }

        binding.saveButton.setOnClickListener {
            val name = binding.nameEditText.text.toString().trim()
            val surname = binding.surnameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()

            val currentParent = Parent(name, surname, phone)
            editProfileViewModel.updateParent(currentParent,
                onSuccess = { message ->
                    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                    val fragmentManager = requireActivity().supportFragmentManager

                    val currentFragment = fragmentManager.findFragmentById(R.id.frameLayout)
                    if (currentFragment != null){
                        fragmentManager.beginTransaction().remove(currentFragment).commit()
                    }

                    fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, PersonalAccountFragment())
                        .addToBackStack(null)
                        .commit()
                },
                onError = { message ->
                    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                })
        }
    }
}