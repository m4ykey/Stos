package com.m4ykey.stos.question.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class QuestionDto(
    val has_more: Boolean,
    val items: List<QuestionItemDto>
)