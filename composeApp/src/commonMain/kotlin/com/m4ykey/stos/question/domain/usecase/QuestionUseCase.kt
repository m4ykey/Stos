package com.m4ykey.stos.question.domain.usecase

import androidx.paging.PagingData
import com.m4ykey.stos.question.domain.model.Question
import com.m4ykey.stos.question.domain.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow

class QuestionUseCase(
    private val repository: QuestionRepository
) {

    fun getQuestions(page : Int, pageSize : Int, sort : String) : Flow<PagingData<Question>> {
        return repository.getQuestions(page, pageSize, sort)
    }

}