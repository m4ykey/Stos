package com.m4ykey.stos.question.mapper

import com.m4ykey.stos.question.domain.model.BadgeCounts
import com.m4ykey.stos.question.domain.model.Owner
import com.m4ykey.stos.question.domain.model.QuestionItem
import com.m4ykey.stos.question.network.model.BadgeCountsDto
import com.m4ykey.stos.question.network.model.OwnerDto
import com.m4ykey.stos.question.network.model.QuestionItemDto

fun BadgeCountsDto.toBadgeCounts() : BadgeCounts {
    return BadgeCounts(
        gold = gold ?: 0,
        bronze = bronze ?: 0,
        silver = silver ?: 0
    )
}

fun OwnerDto.toOwner() : Owner {
    return Owner(
        displayName = display_name.orEmpty(),
        link = link.orEmpty(),
        profileImage = profile_image.orEmpty(),
        reputation = reputation ?: 0,
        userId = user_id ?: 0,
        badgeCounts = badge_counts?.toBadgeCounts() ?: BadgeCounts.EMPTY
    )
}

fun QuestionItemDto.toQuestionItem() : QuestionItem {
    return QuestionItem(
        bodyMarkdown = body_markdown.orEmpty(),
        creationDate = creation_date ?: 0,
        title = title.orEmpty(),
        viewCount = view_count ?: 0,
        upVoteCount = up_vote_count ?: 0,
        downVoteCount = down_vote_count ?: 0,
        questionId = question_id ?: 0,
        closedDate = closed_date ?: 0,
        closedReason = closed_reason.orEmpty(),
        answerCount = answer_count ?: 0,
        owner = owner?.toOwner() ?: Owner.EMPTY
    )
}