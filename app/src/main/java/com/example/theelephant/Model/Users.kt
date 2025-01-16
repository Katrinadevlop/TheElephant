package com.example.theelephant.Model

interface IUser {
    val name: String
    val surname: String
    val phone: String
    val password: String
}

data class Parent(
    override val name: String,
    override val surname: String,
    override val phone: String,
    override val password: String,
) : IUser

data class Specialist(
    override val name: String,
    override val surname: String,
    override val phone: String,
    override val password: String,
    val role: Role,
    val specialization: String,
) : IUser {
    enum class Role {
        PSYCHOLOGIST,
        SPEECH_THERAPIST,
        NEUROPSYCHOLOGIST,
        ТEURODEFECTOLOGIST,
        TOMATIS,
        MASSEUR
    }
}
