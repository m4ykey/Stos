package com.m4ykey.stos.question.presentation.components

import androidx.compose.runtime.Composable
import kmp_stos.composeapp.generated.resources.Res
import kmp_stos.composeapp.generated.resources.days
import kmp_stos.composeapp.generated.resources.hours
import kmp_stos.composeapp.generated.resources.min
import kmp_stos.composeapp.generated.resources.sec
import org.jetbrains.compose.resources.stringResource
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
        days > 0 -> "$days ${stringResource(Res.string.days)}"
        minutes > 0 -> "$minutes ${stringResource(Res.string.min)}"
        hours > 0 -> "$hours ${stringResource(Res.string.hours)}"
        else -> "$seconds ${stringResource(Res.string.sec)}"
    }
}