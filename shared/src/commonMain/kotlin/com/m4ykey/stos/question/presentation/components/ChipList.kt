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
import com.m4ykey.stos.core.SortOption
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import stos.shared.generated.resources.Res
import stos.shared.generated.resources.ic_check

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
fun <T: SortOption> ChipList(
    modifier : Modifier = Modifier,
    selectedChip : T,
    onChipSelected : (T) -> Unit,
    availableSorts : List<T>
) {
    LazyRow(modifier = modifier.padding(horizontal = 5.dp)) {
        items(availableSorts) { key ->
            ChipItem(
                title = stringResource(key.labelRes),
                selected = selectedChip == key,
                onSelect = { onChipSelected(key) },
                modifier = Modifier.padding(horizontal = 5.dp)
            )
        }
    }
}