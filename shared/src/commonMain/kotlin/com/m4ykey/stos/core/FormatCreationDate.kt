package com.m4ykey.stos.core

import kotlin.time.Clock
import kotlin.time.Instant

fun formatCreationDate(date : Long) : String {
    val now = Clock.System.now()
    val creationDate = Instant.fromEpochSeconds(date)
    val difference = now - creationDate

    val days = difference.inWholeDays
    val hours = difference.inWholeHours % 24
    val minutes = difference.inWholeMinutes % 60
    val seconds = difference.inWholeSeconds % 60

    return when {
        days > 0 -> "$days days"
        hours > 0 -> "$hours h."
        minutes > 0 -> "$minutes min"
        else -> "$seconds sec."
    }
}