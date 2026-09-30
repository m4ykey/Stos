package com.m4ykey.stos.network

import org.koin.dsl.module

val networkModule = module {

    single { NetworkClient.create() }

}