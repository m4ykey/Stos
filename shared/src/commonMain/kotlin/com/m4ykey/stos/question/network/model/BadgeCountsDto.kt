package com.m4ykey.stos.question.network.model

import kotlinx.serialization.Serializable

@Serializable
data class BadgeCountsDto(
    val bronze: Int? = null,
    val gold: Int? = null,
    val silver: Int? = null
)