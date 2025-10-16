package com.example.myapplication.data.repository

import com.example.myapplication.data.source.remote.ApiService
import com.example.myapplication.domain.repository.UserRepository
import com.example.myapplication.domain.model.User
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    override suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }

    override suspend fun getUser(id: Int): User {
        return apiService.getUser(id)
    }
}
