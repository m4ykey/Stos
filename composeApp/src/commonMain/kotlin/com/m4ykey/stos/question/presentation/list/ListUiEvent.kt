package com.m4ykey.stos.question.presentation.list

interface ListUiEvent {
    data class ChangeSort(val sort: QuestionSort) : ListUiEvent
}