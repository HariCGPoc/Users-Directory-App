package com.example.myapplication

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.myapplication.domain.usecase.GetUsersUseCase
import com.example.myapplication.presentation.screen.DetailsScreen
import com.example.myapplication.presentation.user.UserViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: UserViewModel
    @Before
    fun setup() {
        val repository = FakeUserRepository()
        val getUserUseCase = GetUsersUseCase(repository)
        viewModel = UserViewModel(getUserUseCase)
    }

    @Test
    fun detailsScreen_displaysUserDetails() {
        composeTestRule.setContent {
            DetailsScreen(userId = 1, viewModel = viewModel)
        }

        // Wait for recomposition and data fetch
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            viewModel.user.value != null
        }

        composeTestRule.onNodeWithText("Name").assertIsDisplayed()
        composeTestRule.onNodeWithText("Emily Johnson").assertIsDisplayed()
        composeTestRule.onNodeWithText("Username").assertIsDisplayed()
        composeTestRule.onNodeWithText("Email").assertIsDisplayed()
        composeTestRule.onNodeWithText("Company").assertIsDisplayed()
    }
}