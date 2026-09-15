package com.m4ykey.stos.question.domain.model

data class QuestionItem(
    val answerCount : Int,
    val bodyMarkdown : String,
    val commentCount : Int,
    val creationDate : Int,
    val downVoteCount : Int,
    val owner : Owner,
    val questionId : Int,
    val title : String,
    val upVoteCount : Int,
    val viewCount : Int
)
