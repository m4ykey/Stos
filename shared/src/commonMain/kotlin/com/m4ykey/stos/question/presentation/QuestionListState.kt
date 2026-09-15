package com.m4ykey.stos.question.presentation

import com.m4ykey.stos.question.domain.model.QuestionItem

data class QuestionListState(
    val error : Throwable? = null,
    val currentPage : Int = 1,
    val isLoading : Boolean = false,
    val questions : List<QuestionItem> = emptyList(),
    val sort : QuestionSort = QuestionSort.ACTIVITY
)
