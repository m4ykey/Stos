package com.m4ykey.stos.question.presentation.state

import com.m4ykey.stos.question.domain.model.QuestionSort

data class QuestionStateFlow(
    val sort : QuestionSort = QuestionSort.HOT
)