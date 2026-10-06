package com.m4ykey.stos.question.presentation.state

import com.m4ykey.stos.question.domain.model.QuestionDetail

data class QuestionDetailState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val item : QuestionDetail? = null
)
