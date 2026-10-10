package com.m4ykey.stos.user.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m4ykey.stos.core.network.ApiResult
import com.m4ykey.stos.user.domain.repository.UserRepository
import com.m4ykey.stos.user.presentation.state.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _userState = MutableStateFlow(UserState())
    val userState = _userState.asStateFlow()

    fun getUserById(userId : Int) {
        viewModelScope.launch {
            repository.getUserById(userId)
                .onStart {
                    _userState.update {
                        it.copy(
                            isLoading = true,
                            error = null
                        )
                    }
                }
                .catch { e ->
                    _userState.update {
                        it.copy(
                            isLoading = false,
                            error = e.message
                        )
                    }
                }
                .collect { result ->
                    when (result) {
                        is ApiResult.Success -> {
                            _userState.update {
                                it.copy(
                                    isLoading = false,
                                    error = null,
                                    user = result.data
                                )
                            }
                        }
                        is ApiResult.Failure -> {
                            _userState.update {
                                it.copy(
                                    isLoading = false,
                                    error = result.exception.message
                                )
                            }
                        }
                    }
                }
        }
    }

    fun onRetryUserState(userId : Int) {
        getUserById(userId)
    }

}