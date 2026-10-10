package com.m4ykey.stos.question.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.m4ykey.stos.core.network.ApiResult
import com.m4ykey.stos.core.network.safeApi
import com.m4ykey.stos.core.paging.pagingConfig
import com.m4ykey.stos.question.domain.model.QuestionDetail
import com.m4ykey.stos.question.domain.model.QuestionItem
import com.m4ykey.stos.question.domain.repository.QuestionRepository
import com.m4ykey.stos.question.mapper.toQuestionDetail
import com.m4ykey.stos.question.network.paging.QuestionPagingSource
import com.m4ykey.stos.question.network.service.RemoteQuestionService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

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

    override suspend fun getQuestionById(questionId: Int): Flow<ApiResult<QuestionDetail>> {
        return flow {
            val result = safeApi { service.getQuestionById(id = questionId) }

            when (result) {
                is ApiResult.Success -> {
                    val questions = result.data.items.map { it.toQuestionDetail() }.firstOrNull()
                    if (questions != null) {
                        emit(ApiResult.Success(questions))
                    }
                }
                is ApiResult.Failure -> {
                    emit(ApiResult.Failure(result.exception))
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}