package com.m4ykey.stos.question.network.model

import kotlinx.serialization.Serializable

@Serializable
data class OwnerDto(
    val badge_counts: BadgeCountsDto? = null,
    val display_name: String? = null,
    val link: String? = null,
    val profile_image: String? = null,
    val reputation: Int? = null,
    val user_id: Int? = null
)