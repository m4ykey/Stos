package com.m4ykey.stos.question.presentation.state

data class QuestionStateFlow(
    val isLoading : Boolean = false,
    val error : String? = null
)