package com.example.theelephant.data.model

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