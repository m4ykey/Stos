package com.m4ykey.stos.question.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.m4ykey.stos.question.domain.model.QuestionSort
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import stos.shared.generated.resources.Res
import stos.shared.generated.resources.activity
import stos.shared.generated.resources.creation
import stos.shared.generated.resources.hot
import stos.shared.generated.resources.ic_check
import stos.shared.generated.resources.month
import stos.shared.generated.resources.votes
import stos.shared.generated.resources.week

@Composable
fun ChipItem(
    title : String,
    selected : Boolean,
    onSelect : (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        leadingIcon = {
            if (selected) {
                Icon(
                    modifier = Modifier.size(14.dp),
                    contentDescription = null,
                    painter = painterResource(Res.drawable.ic_check)
                )
            }
        },
        modifier = modifier.wrapContentWidth(),
        selected = selected,
        onClick = { onSelect(!selected) },
        label = { Text(text = title) }
    )
}

@Composable
fun ChipList(
    modifier : Modifier = Modifier,
    selectedChip : QuestionSort,
    onChipSelected : (QuestionSort) -> Unit,
    availableSorts : List<QuestionSort>
) {
    LazyRow(modifier = Modifier.padding(horizontal = 5.dp)) {
        items(availableSorts) { key ->
            val label = key.getLabel()

            ChipItem(
                title = label,
                selected = selectedChip == key,
                onSelect = {
                    if (availableSorts.contains(key))
                        onChipSelected(key)
                },
                modifier = Modifier.padding(horizontal = 5.dp)
            )
        }
    }
}

@Composable
private fun QuestionSort.getLabel() : String {
    return when (this) {
        QuestionSort.HOT -> stringResource(Res.string.hot)
        QuestionSort.ACTIVITY -> stringResource(Res.string.activity)
        QuestionSort.VOTES -> stringResource(Res.string.votes)
        QuestionSort.CREATION -> stringResource(Res.string.creation)
        QuestionSort.WEEK -> stringResource(Res.string.week)
        QuestionSort.MONTH -> stringResource(Res.string.month)
    }
}