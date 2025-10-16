package com.example.myapplication.data.source.remote

import com.example.myapplication.domain.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): User
}
