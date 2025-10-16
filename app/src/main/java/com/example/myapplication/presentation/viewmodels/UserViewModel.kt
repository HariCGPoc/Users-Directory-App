package com.example.myapplication.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.usecase.GetUsersUseCase
import com.example.myapplication.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _usersList = MutableStateFlow<List<User?>>(emptyList())
    val usersList: StateFlow<List<User?>> = _usersList

    private val _userDetail = MutableStateFlow<User?>(null)
    val userDetail: StateFlow<User?> get() = _userDetail

    init {
        viewModelScope.launch {
            _isLoading.value = true
            _usersList.value = getUsersUseCase()
            _isLoading.value = false
        }
    }

    fun fetchUser(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _userDetail.value = getUsersUseCase.getUser(id)
            _isLoading.value = false
        }
    }
}
