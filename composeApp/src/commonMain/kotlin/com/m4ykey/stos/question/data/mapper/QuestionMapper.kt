package com.m4ykey.stos.question.data.mapper

import com.m4ykey.stos.question.data.network.model.BadgeCountsDto
import com.m4ykey.stos.question.data.network.model.QuestionDto
import com.m4ykey.stos.question.data.network.model.QuestionOwnerDto
import com.m4ykey.stos.question.domain.model.BadgeCounts
import com.m4ykey.stos.question.domain.model.Question
import com.m4ykey.stos.question.domain.model.QuestionOwner

fun BadgeCountsDto.toDomain() = BadgeCounts(
    bronze = bronze ?: 0,
    silver = silver ?: 0,
    gold = gold ?: 0
)

fun QuestionOwnerDto.toDomain() = QuestionOwner(
    link = link.orEmpty(),
    userId = userId ?: 0,
    displayName = displayName.orEmpty(),
    profileImage = profileImage.orEmpty(),
    reputation = reputation ?: 0,
    badgeCounts = badgeCounts?.toDomain() ?: BadgeCounts.EMPTY
)

fun QuestionDto.toDomain() = Question(
    title = title.orEmpty(),
    viewCount = viewCount ?: 0,
    questionId = questionId ?: 0,
    upVoteCount = upVoteCount ?: 0,
    downVoteCount = downVoteCount ?: 0,
    answerCount = answerCount ?: 0,
    creationDate = creationDate ?: 0,
    owner = owner?.toDomain() ?: QuestionOwner.EMPTY
)