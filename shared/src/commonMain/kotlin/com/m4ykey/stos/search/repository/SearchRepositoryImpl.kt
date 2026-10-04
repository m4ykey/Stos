package com.m4ykey.stos.search.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.m4ykey.stos.core.paging.pagingConfig
import com.m4ykey.stos.question.domain.model.QuestionItem
import com.m4ykey.stos.search.domain.SearchRepository
import com.m4ykey.stos.search.network.paging.SearchPagingSource
import com.m4ykey.stos.search.network.service.RemoteSearchService
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(
    private val service : RemoteSearchService
) : SearchRepository {

    override fun searchQuestions(inTitle: String): Flow<PagingData<QuestionItem>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                SearchPagingSource(service, inTitle)
            }
        ).flow
    }
}