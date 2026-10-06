package com.m4ykey.stos.question.domain.model

data class QuestionDetail(
    val title : String,
    val link : String,
    val bodyMarkdown : String,
    val questionId : Int,
    val creationDate : Int,
    val lastActivityDate : Int,
    val answerCount : Int,
    val upVoteCount : Int,
    val downVoteCount : Int,
    val viewCount : Int,
    val commentCount : Int,
    val owner : Owner,
    val tags : List<String>
)
