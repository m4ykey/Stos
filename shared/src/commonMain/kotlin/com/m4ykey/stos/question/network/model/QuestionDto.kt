package com.m4ykey.stos.question.network.model

import kotlinx.serialization.Serializable

@Serializable
data class QuestionDto(
    val has_more: Boolean,
    val items: List<QuestionItemDto>
)