package com.m4ykey.stos.question.network.model

import kotlinx.serialization.Serializable

@Serializable
data class QuestionItemDto(
    val answer_count: Int? = null,
    val body_markdown: String? = null,
    val closed_date: Int? = null,
    val closed_reason: String? = null,
    val comment_count: Int? = null,
    val creation_date: Int? = null,
    val down_vote_count: Int? = null,
    val owner: OwnerDto? = null,
    val question_id: Int? = null,
    val title: String? = null,
    val up_vote_count: Int? = null,
    val view_count: Int? = null
)