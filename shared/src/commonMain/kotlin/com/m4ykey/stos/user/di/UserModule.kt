package com.m4ykey.stos.user.di

import com.m4ykey.stos.user.presentation.UserViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val userModule = module {
    viewModelOf(::UserViewModel)
}