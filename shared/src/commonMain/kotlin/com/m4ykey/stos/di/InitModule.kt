package com.m4ykey.stos.di

import com.m4ykey.stos.network.networkModule
import com.m4ykey.stos.question.di.questionModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

val modules = listOf(
    networkModule,
    questionModule
)

fun initModule(config : KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(platformModule, *modules.toTypedArray())
    }
}