package com.m4ykey.stos.question.domain.repository

import androidx.paging.PagingData
import com.m4ykey.stos.question.domain.model.QuestionItem
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {

    fun getQuestions() : Flow<PagingData<QuestionItem>>

}