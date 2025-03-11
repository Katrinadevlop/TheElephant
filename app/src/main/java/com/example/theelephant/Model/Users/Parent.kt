package com.example.theelephant.Model.Users

data class Parent(
    override val name: String,
    override val surname: String,
    override val phone: String,
    override val password: String,
) : IUser