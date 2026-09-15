package com.m4ykey.stos.question.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class OwnerDto(
    val badge_counts: BadgeCountsDto,
    val display_name: String,
    val link: String,
    val profile_image: String,
    val reputation: Int,
    val user_id: Int
)