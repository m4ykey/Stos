package com.m4ykey.stos.question.presentation.list

sealed interface QuestionListAction {
    data class OnSortClick(val sort : QuestionSort) : QuestionListAction
}