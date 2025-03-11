package com.example.theelephant.View.Fragments

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.theelephant.Model.DataBase.FireBase
import com.example.theelephant.R
import com.example.theelephant.ViewModel.UserAuthorizationViewModel
import com.example.theelephant.ViewModel.UserRegistrationViewModel
import com.example.theelephant.databinding.FragmentUserAuthorizationBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class UserAuthorizationFragment : Fragment() {

    private lateinit var binding: FragmentUserAuthorizationBinding
    private val userAuthorizationViewModel: UserAuthorizationViewModel
        get() = UserAuthorizationViewModel(FireBase())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentUserAuthorizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val signInLauncher =
        registerForActivityResult(FirebaseAuthUIActivityResultContract()) { res ->
            onSignInResult(res)
        }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            val user = FirebaseAuth.getInstance().currentUser
            user?.let {
                val phoneNumber = it.phoneNumber
                val uid = it.uid
                if (phoneNumber != null) {
                    FireBase().getParentByPhone(phoneNumber) { parent ->
                        if (parent != null) {
                            findNavController().navigate(R.id.calendarButtonMenu)
                        } else {
                            Toast.makeText(requireContext(), "Пользователь не найден", Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Номер телефона не найден", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Log.e("AuthError", "Ошибка: ${response?.error?.message}")
            Toast.makeText(requireContext(), "Ошибка входа: ${response?.error?.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startPhoneSignIn() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.PhoneBuilder().build()
        )

        val signInIntent =
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers)
                .build()
        signInLauncher.launch(signInIntent)
    }

    private fun startGoogleSignIn() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        val signInIntent =
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers)
                .build()
        signInLauncher.launch(signInIntent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.authorizationButton.setOnClickListener {
            val phone = binding.editTextPhone.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            userAuthorizationViewModel.checkParent(
                phone, password,
                onSuccess = {
                    findNavController().navigate(R.id.navigation_graph_2)
                    findNavController().popBackStack(R.id.calendarButtonMenu, true)
                },
                onError = { errorMessage ->
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
                }
            )
        }

        binding.phoneSignInButton.setOnClickListener {
            startPhoneSignIn()
        }

        binding.googleSignInButton.setOnClickListener {
            startGoogleSignIn()
        }
    }
}