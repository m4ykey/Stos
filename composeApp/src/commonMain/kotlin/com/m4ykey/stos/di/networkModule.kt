package com.m4ykey.stos.di

import com.m4ykey.stos.core.network.NetworkClient
import org.koin.dsl.module

val networkModule = module {
    single { NetworkClient.create(get()) }
}