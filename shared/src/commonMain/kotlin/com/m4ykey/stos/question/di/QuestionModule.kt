package com.m4ykey.stos.question.di

import com.m4ykey.stos.question.network.service.QuestionService
import com.m4ykey.stos.question.network.service.RemoteQuestionService
import com.m4ykey.stos.question.presentation.QuestionViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val questionModule = module {
    singleOf(::QuestionService) bind RemoteQuestionService::class

    viewModelOf(::QuestionViewModel)
}