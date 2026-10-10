package com.m4ykey.stos.user.mapper

import com.m4ykey.stos.question.domain.model.BadgeCounts
import com.m4ykey.stos.question.network.model.BadgeCountsDto
import com.m4ykey.stos.user.domain.model.Owner
import com.m4ykey.stos.user.network.model.OwnerDto

fun BadgeCountsDto.toBadgeCounts() : BadgeCounts {
    return BadgeCounts(
        gold = gold ?: 0,
        bronze = bronze ?: 0,
        silver = silver ?: 0
    )
}

fun OwnerDto.toOwner() : Owner {
    return Owner(
        displayName = displayName.orEmpty(),
        link = link.orEmpty(),
        profileImage = profileImage.orEmpty(),
        reputation = reputation ?: 0,
        userId = userId ?: 0,
        badgeCounts = badgeCounts?.toBadgeCounts() ?: BadgeCounts.EMPTY
    )
}