package com.m4ykey.stos.user.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m4ykey.stos.question.domain.model.BadgeCounts

@Composable
fun BadgeCountsRow(
    badgeCounts: BadgeCounts
) {
    val badges = buildList {
        if (badgeCounts.gold > 0) add(Color(0xFFFFCC01) to badgeCounts.gold)
        if (badgeCounts.silver > 0) add(Color(0xFFB4B8BC) to badgeCounts.silver)
        if (badgeCounts.bronze > 0) add(Color(0xFFD1A684) to badgeCounts.bronze)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        badges.forEach { (color, count) ->
            BadgeCountsItem(text = count, color = color)
        }
    }
}

@Composable
fun BadgeCountsItem(
    text : Int,
    color : Color
) {
    Row(
        modifier = Modifier.wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ColorCircle(color = color)
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = text.toString(),
            fontSize = 15.sp
        )
    }
}

@Composable
fun ColorCircle(color : Color) {
    Box(
        modifier = Modifier
            .background(color, shape = CircleShape)
            .size(14.dp)
    )
}