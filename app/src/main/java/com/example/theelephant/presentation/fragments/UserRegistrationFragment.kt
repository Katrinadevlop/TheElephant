package com.example.theelephant.presentation.fragments

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.theelephant.data.repository.SpecialistRepository
import com.example.theelephant.data.model.Parent
import com.example.theelephant.R
import com.example.theelephant.data.repository.ParentRepository
import com.example.theelephant.presentation.viewModel.UserRegistrationViewModel
import com.example.theelephant.databinding.FragmentUserRegistrationBinding
import com.example.theelephant.domain.UserRegistrationUseCase

class UserRegistrationFragment : Fragment() {

    private lateinit var binding: FragmentUserRegistrationBinding

    private val userRegistrationViewModel: UserRegistrationViewModel
        get() = UserRegistrationViewModel(
            UserRegistrationUseCase(
                parentRepositoryInterfase = ParentRepository(),
                specialistRepositoryInterface = SpecialistRepository()
            )
        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentUserRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        visibleOrInvisibleEye()

        binding.registrationButton.setOnClickListener {
            val name = binding.nameEditText.text.toString().trim().lowercase()
            val surname = binding.surnameEditText.text.toString().trim().lowercase()
            val phone = binding.editTextPhone.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val passwordRepeat = binding.editTextPasswordRepeat.text.toString().trim()

            userRegistrationViewModel.registerParent(
                parent = Parent(name, surname, phone, password),
                passwordRepeat = passwordRepeat,
                onSuccess = { str ->
                    Toast.makeText(requireContext(), str, Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.navigation_graph_2)
                    findNavController().popBackStack(R.id.userRegistration, true)
                },
                onError = { errorMessage ->
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
                }
            )
        }

        binding.linkTextView.setOnClickListener {
            findNavController().navigate(R.id.userAuthorization)
        }
    }

    private fun visibleOrInvisibleEye() {
        var isPasswordVisible = false
        binding.imageEyePassword.setOnClickListener {
            if (isPasswordVisible) {
                binding.editTextPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                binding.editTextPasswordRepeat.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                binding.imageEyePassword.setImageResource(R.drawable.ic_eye_close)
            } else {
                binding.editTextPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                binding.editTextPasswordRepeat.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                binding.imageEyePassword.setImageResource(R.drawable.ic_eye_open)
            }
            isPasswordVisible = !isPasswordVisible
        }
    }
}
