package com.m4ykey.stos.question.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.m4ykey.stos.core.paging.pagingConfig
import com.m4ykey.stos.question.domain.model.QuestionItem
import com.m4ykey.stos.question.domain.repository.QuestionRepository
import com.m4ykey.stos.question.network.paging.QuestionPagingSource
import com.m4ykey.stos.question.network.service.RemoteQuestionService
import kotlinx.coroutines.flow.Flow

class QuestionRepositoryImpl(
    private val service : RemoteQuestionService
) : QuestionRepository {

    override fun getQuestions(sort : String): Flow<PagingData<QuestionItem>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                QuestionPagingSource(service, sort = sort)
            }
        ).flow
    }
}