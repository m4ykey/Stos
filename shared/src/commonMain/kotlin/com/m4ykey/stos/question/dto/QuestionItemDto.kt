package com.m4ykey.stos.question.dto

import kotlinx.serialization.Serializable

@Serializable
data class QuestionItemDto(
    val answer_count: Int,
    val body_markdown: String,
    val comment_count: Int,
    val creation_date: Int,
    val down_vote_count: Int,
    val owner: OwnerDto,
    val question_id: Int,
    val title: String,
    val up_vote_count: Int,
    val view_count: Int
)