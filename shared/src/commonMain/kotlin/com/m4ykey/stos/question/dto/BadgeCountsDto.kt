package com.m4ykey.stos.question.dto

import kotlinx.serialization.Serializable

@Serializable
data class BadgeCountsDto(
    val bronze: Int,
    val gold: Int,
    val silver: Int
)