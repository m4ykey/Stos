package com.m4ykey.stos.user.di

import com.m4ykey.stos.user.domain.repository.UserRepository
import com.m4ykey.stos.user.network.service.RemoteUserService
import com.m4ykey.stos.user.network.service.UserService
import com.m4ykey.stos.user.presentation.UserViewModel
import com.m4ykey.stos.user.repository.UserRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val userModule = module {
    viewModelOf(::UserViewModel)

    singleOf(::UserService) bind RemoteUserService::class
    singleOf(::UserRepositoryImpl) bind UserRepository::class
}