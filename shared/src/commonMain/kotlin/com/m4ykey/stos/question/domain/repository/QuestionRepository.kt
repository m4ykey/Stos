package com.m4ykey.stos.question.domain.repository

import androidx.paging.PagingData
import com.m4ykey.stos.core.network.ApiResult
import com.m4ykey.stos.question.domain.model.QuestionDetail
import com.m4ykey.stos.question.domain.model.QuestionItem
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {

    fun getQuestions(sort : String) : Flow<PagingData<QuestionItem>>
    suspend fun getQuestionById(questionId : Int) : Flow<ApiResult<QuestionDetail>>

}