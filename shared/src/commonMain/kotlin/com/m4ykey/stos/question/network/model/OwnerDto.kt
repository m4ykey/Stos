package com.m4ykey.stos.question.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OwnerDto(
    @SerialName("badge_counts") val badgeCounts: BadgeCountsDto? = null,
    @SerialName("display_name") val displayName: String? = null,
    val link: String? = null,
    @SerialName("profile_image") val profileImage: String? = null,
    val reputation: Int? = null,
    @SerialName("user_id") val userId: Int? = null
)