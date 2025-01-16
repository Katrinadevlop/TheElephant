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
import com.example.theelephant.Model.Specialist
import com.example.theelephant.R
import com.example.theelephant.ViewModel.ParentViewModel
import com.example.theelephant.databinding.FragmentUserRegistrationBinding

class UserRegistration : Fragment() {

    private lateinit var binding: FragmentUserRegistrationBinding
    private lateinit var parentViewModel: ParentViewModel

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
        parentViewModel = ParentViewModel(parentRepository)

        val specialists = listOf(
            Specialist(
                name = "екатерина",
                surname = "колосова",
                phone = "111",
                password = "htwwye3",
                role = Specialist.Role.PSYCHOLOGIST,
                specialization = "Детский психолог"
            ),
            Specialist(
                name = "Марина",
                surname = "Иванова",
                phone = "79876543210",
                password = "speech2023",
                role = Specialist.Role.SPEECH_THERAPIST,
                specialization = "Коррекция речи"
            ),
            Specialist(
                name = "Сергей",
                surname = "Петров",
                phone = "79999999999",
                password = "neuro@123",
                role = Specialist.Role.NEUROPSYCHOLOGIST,
                specialization = "Нейропсихология"
            ),
            Specialist(
                name = "Алена",
                surname = "Сидорова",
                phone = "79881112233",
                password = "defect@456",
                role = Specialist.Role.ТEURODEFECTOLOGIST,
                specialization = "Дефектология"
            ),
            Specialist(
                name = "Олег",
                surname = "Григорьев",
                phone = "79888884444",
                password = "tomatis@789",
                role = Specialist.Role.TOMATIS,
                specialization = "Метод Томатис"
            ),
            Specialist(
                name = "Алексей",
                surname = "Кузнецов",
                phone = "79771234567",
                password = "massage@2023",
                role = Specialist.Role.MASSEUR,
                specialization = "Детский массаж"
            )
        )

        var isPassowrdVisible = false
        binding.imageEyePassword.setOnClickListener {
            if (isPassowrdVisible) {
                binding.editTextPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                binding.imageEyePassword.setImageResource(R.drawable.ic_eye_close)
            } else {
                binding.editTextPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                binding.imageEyePassword.setImageResource(R.drawable.ic_eye_open)
            }
            isPassowrdVisible = !isPassowrdVisible
        }

        var isPassowrdVisibleRepeat = false
        binding.imageEyePasswordRepeat.setOnClickListener {
            if (isPassowrdVisibleRepeat) {
                binding.editTextPasswordRepeat.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                binding.imageEyePasswordRepeat.setImageResource(R.drawable.ic_eye_close)
            } else {
                binding.editTextPasswordRepeat.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                binding.imageEyePasswordRepeat.setImageResource(R.drawable.ic_eye_open)
            }
            isPassowrdVisibleRepeat = !isPassowrdVisibleRepeat
        }

        binding.registrationButton.setOnClickListener {
            val name = binding.nameEditText.text.toString().trim().lowercase()
            val surname = binding.surnameEditText.text.toString().trim().lowercase()
            val phone = binding.editTextPhone.text.toString().trim().lowercase()
            val password = binding.editTextPassword.text.toString().trim().lowercase()
            val passwordRepeat = binding.editTextPasswordRepeat.text.toString().trim().lowercase()

            if (name.isNotEmpty() && surname.isNotEmpty() && phone.isNotEmpty() && password.isNotEmpty() && passwordRepeat.isNotEmpty()) {
                if (password == passwordRepeat) {
                    val findSpecialist =
                        specialists.find { it.name == name && it.surname == surname && it.phone == phone }
                    if (findSpecialist == null) {
                        parentViewModel.saveParent(name, surname, phone, password)
                        findNavController().navigate(R.id.navigationButton2)
                    } else Toast.makeText(
                        requireContext(),
                        "Специалист уже зарегистрирован",
                        Toast.LENGTH_LONG
                    ).show()
                } else Toast.makeText(requireContext(), "Пароли не совпадают", Toast.LENGTH_LONG)
                    .show()
            } else Toast.makeText(requireContext(), "Не все поля заполнены", Toast.LENGTH_LONG)
                .show()
        }

        binding.linkTextView.setOnClickListener {
            findNavController().navigate(R.id.userAuthorization)
        }
    }
}