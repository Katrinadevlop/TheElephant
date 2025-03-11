package com.example.theelephant.View.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.theelephant.Model.DataBase.FireBase
import com.example.theelephant.R
import com.example.theelephant.ViewModel.UserAuthorizationViewModel
import com.example.theelephant.databinding.FragmentUserAuthorizationBinding

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
    }
}