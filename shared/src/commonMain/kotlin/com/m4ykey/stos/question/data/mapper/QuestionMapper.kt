package com.m4ykey.stos.question.data.mapper

import com.m4ykey.stos.question.domain.model.BadgeCounts
import com.m4ykey.stos.question.domain.model.Owner
import com.m4ykey.stos.question.domain.model.QuestionItem
import com.m4ykey.stos.question.data.network.dto.BadgeCountsDto
import com.m4ykey.stos.question.data.network.dto.OwnerDto
import com.m4ykey.stos.question.data.network.dto.QuestionItemDto

fun BadgeCountsDto.toBadgeCounts() : BadgeCounts {
    return BadgeCounts(
        gold = gold,
        silver = silver,
        bronze = bronze
    )
}

fun OwnerDto.toOwner() : Owner {
    return Owner(
        displayName = display_name,
        userId = user_id,
        profileImage = profile_image,
        reputation = reputation,
        link = link,
        badgeCounts = badge_counts.toBadgeCounts()
    )
}

fun QuestionItemDto.toQuestionItem() : QuestionItem {
    return QuestionItem(
        answerCount = answer_count,
        bodyMarkdown = body_markdown,
        owner = owner.toOwner(),
        creationDate = creation_date,
        downVoteCount = down_vote_count,
        title = title,
        upVoteCount = up_vote_count,
        questionId = question_id,
        viewCount = view_count,
        commentCount = comment_count
    )
}