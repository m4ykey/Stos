package com.m4ykey.stos.question.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.m4ykey.stos.core.views.CenteredContent
import com.m4ykey.stos.question.presentation.list.QuestionSort

@Composable
fun ChipList(
    modifier: Modifier = Modifier,
    selectedChip : QuestionSort,
    onChipSelected : (QuestionSort) -> Unit
) {
    val chipList = QuestionSort.entries.map { sort ->
        sort.getLabel() to sort
    }

    CenteredContent(modifier = modifier) { contentModifier ->
        LazyRow(
            modifier = contentModifier.padding(horizontal = 5.dp)
        ) {
            items(chipList) { (label, sortKey) ->
                ChipItem(
                    title = label,
                    selected = selectedChip == sortKey,
                    onSelect = { onChipSelected(sortKey) },
                    modifier = Modifier.padding(horizontal = 5.dp)
                )
            }
        }
    }
}

@Composable
private fun QuestionSort.getLabel() : String {
    return when (this)  {
        QuestionSort.HOT -> "Hot"
        QuestionSort.ACTIVITY -> "Activity"
        QuestionSort.VOTES -> "Votes"
        QuestionSort.WEEK -> "Week"
        QuestionSort.MONTH -> "Month"
        QuestionSort.CREATION -> "Creation"
    }
}