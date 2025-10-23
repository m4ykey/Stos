package com.m4ykey.stos.question.presentation.components

import androidx.compose.runtime.Composable
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
@Composable
fun formatCreationDate(date : Long) : String {
    val currentSystemTime = Clock.System.now()
    val creationDate = Instant.fromEpochSeconds(date)

    val differenceInSeconds = currentSystemTime - creationDate

    val days = differenceInSeconds.inWholeDays
    val hours = differenceInSeconds.inWholeHours % 24
    val minutes = differenceInSeconds.inWholeMinutes % 60
    val seconds = differenceInSeconds.inWholeSeconds % 60

    return when {
        days > 0 -> "$days Days"
        minutes > 0 -> "$minutes min"
        hours > 0 -> "$hours h"
        else -> "$seconds sec"
    }
}