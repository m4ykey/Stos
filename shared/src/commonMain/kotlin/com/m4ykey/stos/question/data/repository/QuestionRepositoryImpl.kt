package com.m4ykey.stos.question.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.m4ykey.stos.question.data.network.service.RemoteQuestionService
import com.m4ykey.stos.question.data.paging.QuestionPagingSource
import com.m4ykey.stos.question.domain.model.QuestionItem
import com.m4ykey.stos.question.domain.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow

class QuestionRepositoryImpl(
    private val service : RemoteQuestionService
) : QuestionRepository {

    override fun getQuestions(sort : String): Flow<PagingData<QuestionItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 5,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                QuestionPagingSource(
                    service = service,
                    sort = sort,
                    site = "stackoverflow"
                )
            }
        ).flow
    }
}