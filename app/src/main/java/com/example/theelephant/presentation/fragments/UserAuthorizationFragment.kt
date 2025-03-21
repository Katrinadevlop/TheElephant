package com.example.theelephant.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.theelephant.R
import com.example.theelephant.data.repository.ParentRepository
import com.example.theelephant.presentation.viewModel.UserAuthorizationViewModel
import com.example.theelephant.databinding.FragmentUserAuthorizationBinding
import com.example.theelephant.domain.UserAuthorizationUseCase

class UserAuthorizationFragment : Fragment() {

    private lateinit var binding: FragmentUserAuthorizationBinding
    private val userAuthorizationViewModel: UserAuthorizationViewModel
        get() = UserAuthorizationViewModel(UserAuthorizationUseCase(parentRepositoryInterfase = ParentRepository()))

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
                    //TODO val bundle = Bundle().apply { putString("userPhone", phone) }
                    findNavController().navigate(R.id.navigation_graph_2)//bundle
                    findNavController().popBackStack(R.id.calendarButtonMenu, true)
                },
                onError = { errorMessage ->
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
                }
            )
        }
    }
}