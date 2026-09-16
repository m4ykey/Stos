package com.m4ykey.stos.question.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import com.m4ykey.stos.question.domain.model.QuestionItem
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun QuestionItem(
    modifier : Modifier = Modifier,
    item : QuestionItem
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = modifier.fillMaxWidth()) {
            Card(shape = CircleShape) {
                AsyncImage(
                    model = item.owner.profileImage,
                    contentDescription = null
                )
            }
            Column {
                Text(text = item.owner.displayName)
                Text(text = item.owner.reputation.toString())
            }
        }
        Text(text = item.bodyMarkdown)
    }
}

@Composable
fun CountItem(
    icon : DrawableResource,
    count : Int
) {
    Row {
        Icon(
            painter = painterResource(icon),
            contentDescription = null
        )
        Text(text = count.toString())
    }
}
