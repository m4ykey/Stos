package com.m4ykey.stos.question.di

import com.m4ykey.stos.network.NetworkClient
import com.m4ykey.stos.question.data.network.service.QuestionService
import com.m4ykey.stos.question.data.network.service.RemoteQuestionService
import com.m4ykey.stos.question.data.repository.QuestionRepositoryImpl
import com.m4ykey.stos.question.domain.repository.QuestionRepository
import com.m4ykey.stos.question.presentation.QuestionViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val questionModule = module {
    viewModelOf(::QuestionViewModel)

    singleOf(::QuestionRepositoryImpl) bind QuestionRepository::class

    single {
        NetworkClient.create(get())
    }

    single<RemoteQuestionService> {
        QuestionService(get())
    }

}