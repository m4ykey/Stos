package com.m4ykey.stos.user.presentation.state

import com.m4ykey.stos.user.domain.model.Owner

data class UserState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val user : Owner? = null
)
