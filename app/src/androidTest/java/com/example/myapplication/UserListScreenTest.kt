package com.example.myapplication

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.usecase.GetUsersUseCase
import com.example.myapplication.presentation.screen.UserListScreen
import com.example.myapplication.presentation.user.UserViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var viewModel: UserViewModel
    private val testUsers = listOf(
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
            photo = "https://json-server.dev/ai-profiles/86.png"
        )
    )
    @Before
    fun setup() {
        val repository = FakeUserRepository()
        val getUserUseCase = GetUsersUseCase(repository)
        viewModel = UserViewModel(getUserUseCase)
    }
    @Test
    fun lazyColumn_displaysAllUserItems() {

        composeTestRule.setContent {
            UserListScreen(navController = rememberNavController(), viewModel = viewModel)
        }

        // Check LazyColumn is displayed
        composeTestRule.onNodeWithTag("users_list").assertIsDisplayed()

        // Check each user item is displayed
        testUsers.forEach { user ->
            composeTestRule.onNodeWithText("Name: ${user.name}").assertIsDisplayed()
            composeTestRule.onNodeWithText("Email: ${user.email}").assertIsDisplayed()
            composeTestRule.onNodeWithText("Phone: ${user.phone}").assertIsDisplayed()
        }
    }
}
