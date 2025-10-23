package com.m4ykey.stos.question.presentation.list

import com.m4ykey.stos.question.domain.model.Question

data class QuestionListState(
    val currentPage : Int = 1,
    val questions : List<Question> = emptyList(),
    val sort : QuestionSort = QuestionSort.HOT,
    val order : QuestionOrder = QuestionOrder.DESC
)
