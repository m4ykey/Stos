package com.m4ykey.stos.search.domain

import androidx.paging.PagingData
import com.m4ykey.stos.question.domain.model.QuestionItem
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun searchQuestions(inTitle : String) : Flow<PagingData<QuestionItem>>

}