package com.example.theelephant.View.Fragments

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.theelephant.Model.DataBase.DataBase
import com.example.theelephant.Model.DataBase.Repository.ParentRepository
import com.example.theelephant.Model.Parent
import com.example.theelephant.R
import com.example.theelephant.ViewModel.UserRegistrationViewModel
import com.example.theelephant.databinding.FragmentUserRegistrationBinding

class UserRegistration : Fragment() {

    private lateinit var binding: FragmentUserRegistrationBinding
    private lateinit var userRegistrationViewModel: UserRegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentUserRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val parentRepository = ParentRepository(DataBase.getDataBase(requireContext()))
        userRegistrationViewModel = UserRegistrationViewModel(parentRepository)

        visibleInvisileEye()

        binding.registrationButton.setOnClickListener {
            val name = binding.nameEditText.text.toString().trim().lowercase()
            val surname = binding.surnameEditText.text.toString().trim().lowercase()
            val phone = binding.editTextPhone.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val passwordRepeat = binding.editTextPasswordRepeat.text.toString().trim()

            userRegistrationViewModel.registerParent(
                parent = Parent(name, surname, phone, password),
                passwordRepeat = passwordRepeat,
                onSuccess = {
                    findNavController().navigate(R.id.navigationButton2)
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

    fun visibleInvisileEye(){
        var isPassowrdVisible = false
        binding.imageEyePassword.setOnClickListener {
            if (isPassowrdVisible) {
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
            isPassowrdVisible = !isPassowrdVisible
        }
    }
}