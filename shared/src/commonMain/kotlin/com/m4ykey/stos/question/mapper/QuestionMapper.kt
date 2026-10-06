package com.m4ykey.stos.question.mapper

import com.m4ykey.stos.question.domain.model.BadgeCounts
import com.m4ykey.stos.question.domain.model.Owner
import com.m4ykey.stos.question.domain.model.QuestionDetail
import com.m4ykey.stos.question.domain.model.QuestionItem
import com.m4ykey.stos.question.network.model.BadgeCountsDto
import com.m4ykey.stos.question.network.model.OwnerDto
import com.m4ykey.stos.question.network.model.QuestionDetailDto
import com.m4ykey.stos.question.network.model.QuestionItemDto

fun QuestionDetailDto.toQuestionDetail() : QuestionDetail {
    return QuestionDetail(
        answerCount = answerCount ?: 0,
        title = title.orEmpty(),
        bodyMarkdown = bodyMarkdown.orEmpty(),
        commentCount = commentCount ?: 0,
        link = link.orEmpty(),
        viewCount = viewCount ?: 0,
        upVoteCount = upVoteCount ?: 0,
        questionId = questionId ?: 0,
        downVoteCount = downVoteCount ?: 0,
        creationDate = creationDate ?: 0,
        lastActivityDate = lastActivityDate ?: 0,
        owner = owner?.toOwner() ?: Owner.EMPTY,
        tags = tags ?: emptyList()
    )
}

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

fun QuestionItemDto.toQuestionItem() : QuestionItem {
    return QuestionItem(
        bodyMarkdown = bodyMarkdown.orEmpty(),
        creationDate = creationDate ?: 0,
        title = title.orEmpty(),
        viewCount = viewCount ?: 0,
        upVoteCount = upVoteCount ?: 0,
        downVoteCount = downVoteCount ?: 0,
        questionId = questionId ?: 0,
        closedDate = closedDate ?: 0,
        closedReason = closedReason.orEmpty(),
        answerCount = answerCount ?: 0,
        owner = owner?.toOwner() ?: Owner.EMPTY
    )
}