package com.example.myapplication.domain.model

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val company: String,
    val address: String,
    val zip: String,
    val state: String,
    val country: String,
    val photo: String
)

