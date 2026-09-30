package com.m4ykey.stos.question.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.m4ykey.stos.question.domain.model.QuestionItem
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun QuestionItem(
    modifier : Modifier = Modifier,
    item : QuestionItem,
    onQuestionClick : (Int) -> Unit,
    onOwnerClick : (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onQuestionClick(item.questionId) }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable { onOwnerClick(item.owner.userId) }
        ) {
            Card(
                shape = CircleShape
            ) {
                AsyncImage(
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    model = item.owner.profileImage
                )
            }
            Column {
                Text(text = item.owner.displayName)
                Text(text = item.owner.reputation.toString())
            }
        }
        Text(item.bodyMarkdown)
        Row(
            modifier = modifier.fillMaxWidth()
        ) {  }
        Text(item.creationDate.toString())
    }
}

@Composable
fun QuestionInfoItem(
    icon : DrawableResource,
    text : Int
) {
    Row {
        Image(
            painter = painterResource(icon),
            contentDescription = null
        )
        Text(text = text.toString())
    }
}