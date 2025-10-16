package com.example.myapplication

import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.usecase.GetUsersUseCase
import com.example.myapplication.presentation.user.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class UserViewModelTest {

    private lateinit var getUsersUseCase: GetUsersUseCase
    private lateinit var viewModel: UserViewModel

    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getUsersUseCase = mock(GetUsersUseCase::class.java)
        viewModel = UserViewModel(getUsersUseCase)
    }

    @Test
    fun `init should load users and update isLoading`() = runTest {
        val mockUsers = listOf(
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
                phone = "+1-555-123-4567"
            ),
            User(
                id = 2,
                name = "Michele Russel",
                company = "Harber, Barrows and Morar",
                username = "Kameron.Waters73",
                email = "Ludwig47@yahoo.com",
                address = "13002 Brooks Junction",
                zip = "38194-0750",
                state = "Pennsylvania",
                country = "Seychelles",
                phone = "1-497-506-6241 x4514"
            )
        )

        `when`(getUsersUseCase()).thenReturn(mockUsers)

        viewModel = UserViewModel(getUsersUseCase)
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(false, viewModel.isLoading.value)
        assertEquals(mockUsers, viewModel.users.value)
    }

    @Test
    fun `fetchUser should update user and isLoading`() = runTest {
        // Arrange
        val mockUser =  User(
            id = 1,
            name = "Emily Johnson",
            company = "ABC Corporation",
            username = "emily_johnson",
            email = "emily.johnson@abccorporation.com",
            address = "123 Main St",
            zip = "12345",
            state = "California",
            country = "USA",
            phone = "+1-555-123-4567"
        )
        `when`(getUsersUseCase.getUser(1)).thenReturn(mockUser)

        viewModel.fetchUser(1)
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals(false, viewModel.isLoading.value)
        assertEquals(mockUser, viewModel.user.value)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}