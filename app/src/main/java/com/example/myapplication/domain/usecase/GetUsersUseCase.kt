package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.UserRepository
import com.example.myapplication.domain.model.User

import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): List<User> {
        return userRepository.getUsers()
    }

    suspend fun getUser(id: Int): User {
        return userRepository.getUser(id)
    }
}
