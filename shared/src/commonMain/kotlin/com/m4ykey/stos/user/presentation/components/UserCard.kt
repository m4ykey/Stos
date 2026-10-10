package com.m4ykey.stos.user.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun UserCard(
    size : Dp = 32.dp,
    profileImage : String
) {
    Card(
        shape = CircleShape,
        modifier = Modifier.size(size)
    ) {
        AsyncImage(
            contentDescription = null,
            contentScale = ContentScale.Crop,
            model = profileImage
        )
    }
}