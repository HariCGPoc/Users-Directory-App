package com.example.myapplication.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.presentation.user.UserViewModel
import com.example.myapplication.utils.ApplicationConstants


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(userId: Int, viewModel: UserViewModel) {
    val user by viewModel.userDetail.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(userId) {
        viewModel.fetchUser(userId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Details",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        containerColor = Color(0xFFF5F5F5)
    ) { padding ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            user?.let {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(padding)
                        .padding(ApplicationConstants.Medium_16)
                ) {
                    AsyncImage(
                        model = it.photo,
                        contentDescription = "User photo",
                        imageLoader = ImageLoader.Builder(LocalContext.current)
                            .okHttpClient(getUnsafeOkHttpClient())
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .build(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(400.dp)
                            .padding(20.dp), alignment = Alignment.Center,
                        contentScale = ContentScale.Crop
                    )

                    val details = listOf(
                        "Name" to it.name,
                        "Username" to it.username,
                        "Email" to it.email,
                        "Company" to it.company,
                        "Address" to it.address,
                        "Zip" to it.zip,
                        "State" to it.state,
                        "Country" to it.country,
                        "Phone" to it.phone
                    )

                    details.forEach { (label, value) ->
                        DetailItem(label = label, value = value)
                    }

                }
            }
        }
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            color = Color.DarkGray
        )
    }
}
