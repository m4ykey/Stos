package com.m4ykey.stos.question.domain.model

data class QuestionOwner(
    val reputation : Int,
    val userId : Int,
    val link : String,
    val profileImage : String,
    val displayName : String,
    val badgeCounts : BadgeCounts
) {
    companion object {
        val EMPTY = QuestionOwner(
            link = "",
            userId = 0,
            reputation = 0,
            profileImage = "",
            displayName = "",
            badgeCounts = BadgeCounts.EMPTY
        )
    }
}