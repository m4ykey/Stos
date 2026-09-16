package com.m4ykey.stos.di

import com.m4ykey.stos.question.di.questionModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config : KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(questionModule)
    }
}