package com.m4ykey.stos.question.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.m4ykey.stos.core.formatCreationDate
import com.m4ykey.stos.core.formatReputation
import com.m4ykey.stos.question.domain.model.QuestionItem
import com.m4ykey.text_markdown.TextMarkdown
import org.jetbrains.compose.resources.painterResource
import stos.shared.generated.resources.Res
import stos.shared.generated.resources.ic_arrow_down
import stos.shared.generated.resources.ic_arrow_up
import stos.shared.generated.resources.ic_comment
import stos.shared.generated.resources.ic_view

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
                .clickable { onOwnerClick(item.owner.userId) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = CircleShape,
                modifier = Modifier.size(32.dp)
            ) {
                AsyncImage(
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    model = item.owner.profileImage
                )
            }
            Spacer(modifier = modifier.width(10.dp))
            Column {
                TextMarkdown(
                    text = item.owner.displayName,
                    fontSize = 14.sp,
                    alignment = Alignment.TopStart
                )
                Text(
                    text = formatReputation(item.owner.reputation),
                    fontSize = 12.sp
                )
            }
        }
        TextMarkdown(
            alignment = Alignment.TopStart,
            text = item.title
        )
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            QuestionInfoItem(
                text = getVoteCount(item.downVoteCount > 1, item).toInt(),
                icon = getArrowPosition(item.downVoteCount > 1)
            )
            QuestionInfoItem(
                text = item.answerCount,
                icon = painterResource(Res.drawable.ic_comment)
            )
            QuestionInfoItem(
                text = item.viewCount,
                icon = painterResource(Res.drawable.ic_view)
            )
        }
        Text(
            text = formatCreationDate(item.creationDate.toLong()),
            fontSize = 13.sp
        )
    }
}

private fun getVoteCount(isDownVote : Boolean, item : QuestionItem) : String {
    return if (isDownVote) {
        "-${item.downVoteCount}"
    } else {
        "${item.upVoteCount}"
    }
}

@Composable
private fun getArrowPosition(isDownVote: Boolean) : Painter {
    return if (isDownVote) {
        painterResource(Res.drawable.ic_arrow_down)
    } else {
        painterResource(Res.drawable.ic_arrow_up)
    }
}

@Composable
fun QuestionInfoItem(
    icon : Painter,
    text : Int
) {
    val color = MaterialTheme.colorScheme.onSurface

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(14.dp),
            colorFilter = ColorFilter.tint(color = color, blendMode = BlendMode.SrcIn)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = text.toString(),
            fontSize = 14.sp
        )
    }
}