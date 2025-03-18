package com.example.theelephant.data.model

data class Parent(
    val id: String = "",
    override val name: String = "",
    override val surname: String = "",
    override val phone: String = "",
    override val password: String = "",
) : IUser {}