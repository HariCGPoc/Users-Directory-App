package com.example.myapplication

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.domain.repository.UserRepository
import com.example.myapplication.domain.usecase.GetUsersUseCase
import com.example.myapplication.presentation.screen.UserListScreen
import com.example.myapplication.presentation.user.UserViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test



class UserListScreenTest{

    @get:Rule
    val composeTestRule = createComposeRule()
     lateinit var getUsersUseCaseUI: GetUsersUseCase
     lateinit var viewModel: UserViewModel
     lateinit var repository: UserRepository

    @Before
    fun setup() {
        repository = FakeUserRepository()
        getUsersUseCaseUI = GetUsersUseCase(repository)
        viewModel = UserViewModel(getUsersUseCaseUI)
    }
    @Test
    fun userListScreen_displaysUserDetails() {
        composeTestRule.setContent {
            UserListScreen(
                 rememberNavController(),
                 viewModel,

            )
        }

        composeTestRule.onNodeWithText("Name:  Emily Johnson").assertIsDisplayed()
        composeTestRule.onNodeWithText("Email: emily.johnson@abccorporation.com").assertIsDisplayed()
        composeTestRule.onNodeWithText("Phone: +1-555-123-4567").assertIsDisplayed()
    }
}