package com.example.theelephant.View.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.theelephant.Model.DataBase.DataBase
import com.example.theelephant.Model.DataBase.Repository.ParentRepository
import com.example.theelephant.R
import com.example.theelephant.ViewModel.UserAuthorizationViewModel
import com.example.theelephant.ViewModel.UserRegistrationViewModel
import com.example.theelephant.databinding.FragmentUserAuthorizationBinding

class UserAuthorizationFragment : Fragment() {

    private lateinit var binding: FragmentUserAuthorizationBinding
    private lateinit var userAuthorizationViewModel: UserAuthorizationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentUserAuthorizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val parentRepository = ParentRepository(DataBase.getDataBase(requireContext()))
        userAuthorizationViewModel = UserAuthorizationViewModel(parentRepository)

        binding.registrationButton.setOnClickListener {
            val phone = binding.editTextPhone.toString().trim()
            val password = binding.editTextPassword.toString().trim()

            userAuthorizationViewModel.checkParent(
                phone, password,
                onSuccess = {
                    findNavController().navigate(R.id.navigation_graph_2)
                    findNavController().popBackStack(R.id.userRegistration, true)
                },
                onError = { errorMessage ->
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
                }
            )
        }
    }
}