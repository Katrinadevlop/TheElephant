package com.example.theelephant.View.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.theelephant.databinding.FragmentUserAuthorizationBinding

class UserAuthorizationFragment : Fragment() {

    private lateinit var binding: FragmentUserAuthorizationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentUserAuthorizationBinding.inflate(inflater, container, false)
        return binding.root
    }
}