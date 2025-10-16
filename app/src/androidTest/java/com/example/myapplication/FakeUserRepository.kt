package com.example.myapplication

import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.repository.UserRepository

class FakeUserRepository : UserRepository {
    override suspend fun getUsers(): List<User> {
        return listOf(
            User(
                id = 1,
                name = "Emily Johnson",
                company = "ABC Corporation",
                username = "emily_johnson",
                email = "emily.johnson@abccorporation.com",
                address = "123 Main St",
                zip = "12345",
                state = "California",
                country = "USA",
                phone = "+1-555-123-4567",
                photo="https://json-server.dev/ai-profiles/86.png"
            )
        )
    }
    override suspend fun getUser(id: Int): User {

        return  User(
            id = 1,
            name = "Emily Johnson",
            company = "ABC Corporation",
            username = "emily_johnson",
            email = "emily.johnson@abccorporation.com",
            address = "123 Main St",
            zip = "12345",
            state = "California",
            country = "USA",
            phone = "+1-555-123-4567",
            photo="https://json-server.dev/ai-profiles/86.png"
        )

    }
}