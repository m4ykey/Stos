package com.m4ykey.stos.question.dto

import kotlinx.serialization.Serializable

@Serializable
data class QuestionDto(
    val has_more: Boolean,
    val items: List<QuestionItemDto>,
    val page: Int,
    val page_size: Int
)