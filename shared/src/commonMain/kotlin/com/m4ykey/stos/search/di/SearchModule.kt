package com.m4ykey.stos.search.di

import com.m4ykey.stos.search.domain.SearchRepository
import com.m4ykey.stos.search.network.service.RemoteSearchService
import com.m4ykey.stos.search.network.service.SearchService
import com.m4ykey.stos.search.presentation.SearchViewModel
import com.m4ykey.stos.search.repository.SearchRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val searchModule = module {
    singleOf(::SearchService) bind RemoteSearchService::class
    singleOf(::SearchRepositoryImpl) bind SearchRepository::class

    viewModelOf(::SearchViewModel)
}