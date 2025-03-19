package com.example.theelephant.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import com.example.theelephant.R
import com.example.theelephant.databinding.FragmentNavigationButtonBinding

class NavigationButtonFragment : Fragment() {

    private lateinit var binding: FragmentNavigationButtonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNavigationButtonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadFragment(CalendarRecordingFragment())

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.calendarButtonMenu -> {
                    loadFragment(CalendarRecordingFragment())
                    true
                }

                R.id.profileButtonMenu -> {
                    loadFragment(PersonalAccountFragment())
                    true
                }

                else -> false
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            showExitDialog()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }

    private fun showExitDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Выйти из приложения?").setCancelable(false)
            .setPositiveButton("Да") { dialog, id ->
                requireActivity().finish()
            }.setNegativeButton("Нет") { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
}