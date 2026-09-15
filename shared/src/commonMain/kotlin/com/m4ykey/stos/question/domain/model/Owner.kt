package com.m4ykey.stos.question.domain.model

data class Owner(
    val badgeCounts: BadgeCounts,
    val displayName : String,
    val link : String,
    val profileImage : String,
    val reputation : Int,
    val userId : Int
)
