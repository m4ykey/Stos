package com.m4ykey.stos.question.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionItemDto(
    @SerialName("answer_count") val answerCount: Int? = null,
    @SerialName("body_markdown") val bodyMarkdown: String? = null,
    @SerialName("closed_date") val closedDate: Int? = null,
    @SerialName("closed_reason") val closedReason: String? = null,
    @SerialName("comment_count") val commentCount: Int? = null,
    @SerialName("creation_date") val creationDate: Int? = null,
    @SerialName("down_vote_count") val downVoteCount: Int? = null,
    val owner: OwnerDto? = null,
    @SerialName("question_id") val questionId: Int? = null,
    val title: String? = null,
    @SerialName("up_vote_count") val upVoteCount: Int? = null,
    @SerialName("view_count") val viewCount: Int? = null
)