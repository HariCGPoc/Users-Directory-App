package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
    suspend fun getUser(id: Int): User
}
