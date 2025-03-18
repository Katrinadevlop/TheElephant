package com.example.theelephant.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.theelephant.databinding.FragmentPersonalAccountBinding

class PersonalAccountFragment : Fragment() {

    private lateinit var binding: FragmentPersonalAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPersonalAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userPhone = arguments?.getString("userPhone")
        binding.phoneTextView.text = userPhone
    }
}