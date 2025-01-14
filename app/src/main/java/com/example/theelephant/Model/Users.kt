package com.example.theelephant.Model

sealed class UserRegistration(
    val id: Int,
    val name: String,
    val surname: String,
    val phone: String,
    val password: String,
)

sealed class User(
    id: Int,
    name: String,
    surname: String,
    phone: String,
    password: String,
) : UserRegistration(id, name, surname, phone, password) {

    class Parent(
        id: Int,
        name: String,
        surname: String,
        phone: String,
        password: String,
    ) : User(id, name, surname, phone, password){
        val role: Role = Role.PARENT
    }

    sealed class Specialist(
        id: Int,
        name: String,
        surname: String,
        phone: String,
        password: String,
        val role: Role,
        val specialization: String,
    ) : User(id, name, surname, phone, password)

    class Psychologist(
        id: Int,
        name: String,
        surname: String,
        phone: String,
        password: String,
        role: Role,
        specialization: String,
    ) : Specialist(id, name, surname, phone, password, Role.PSYCHOLOGIST, specialization)

    class SpeechTherapist(
        id: Int,
        name: String,
        surname: String,
        phone: String,
        password: String,
        role: Role,
        specialization: String,
    ) : Specialist(id, name, surname, phone, password, Role.SPEECH_THERAPIST, specialization)

    class Neuropsychologist(
        id: Int,
        name: String,
        surname: String,
        phone: String,
        password: String,
        role: Role,
        specialization: String,
    ) : Specialist(id, name, surname, phone, password, Role.NEUROPSYCHOLOGIST, specialization)

    class Тeurodefectologist(
        id: Int,
        name: String,
        surname: String,
        phone: String,
        password: String,
        role: Role,
        specialization: String,
    ) : Specialist(id, name, surname, phone, password, Role.ТEURODEFECTOLOGIST, specialization)

    class Tomatis(
        id: Int,
        name: String,
        surname: String,
        phone: String,
        password: String,
        role: Role,
        specialization: String,
    ) : Specialist(id, name, surname, phone, password, Role.TOMATIS, specialization)

    class MassageTherapist(
        id: Int,
        name: String,
        surname: String,
        phone: String,
        password: String,
        role: Role,
        specialization: String,
    ) : Specialist(id, name, surname, phone, password, Role.MASSEUR, specialization)

    enum class Role {
        PARENT,
        PSYCHOLOGIST,
        SPEECH_THERAPIST,
        NEUROPSYCHOLOGIST,
        ТEURODEFECTOLOGIST,
        TOMATIS,
        MASSEUR
    }
}