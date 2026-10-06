package com.m4ykey.stos.question.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionDetailDto(
    val title : String? = null,
    val link : String? = null,
    @SerialName("body_markdown") val bodyMarkdown : String? = null,
    @SerialName("question_id") val questionId : Int? = null,
    @SerialName("creation_date") val creationDate : Int? = null,
    @SerialName("last_activity_date") val lastActivityDate : Int? = null,
    @SerialName("answer_count") val answerCount : Int? = null,
    @SerialName("up_vote_count") val upVoteCount : Int? = null,
    @SerialName("down_vote_count") val downVoteCount : Int? = null,
    @SerialName("view_count") val viewCount : Int? = null,
    @SerialName("comment_count") val commentCount : Int? = null,
    val owner : OwnerDto? = null,
    val tags: List<String>? = emptyList()
)
