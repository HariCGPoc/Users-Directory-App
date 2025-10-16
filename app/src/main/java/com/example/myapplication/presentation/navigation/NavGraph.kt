package com.example.myapplication.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.domain.model.User
import com.example.myapplication.presentation.screen.DetailsScreen
import com.example.myapplication.presentation.screen.UserListScreen
import com.example.myapplication.presentation.user.UserViewModel

@Composable
fun NavGraph(startDestination: String = "user_screen", users: List<User?>) {
    val navController = rememberNavController()
    val userViewModel: UserViewModel = viewModel()

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("user_screen") {
                UserListScreen(navController)
            }
            composable(
                "details_screen/{userId}",
                arguments = listOf(navArgument("userId") { type = NavType.IntType })
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getInt("userId")
                val user = users.firstOrNull { it?.id == userId }
                user?.let {
                    DetailsScreen(
                        userId = user.id,
                        viewModel = userViewModel
                    )
                }
            }
        }
    }
}
