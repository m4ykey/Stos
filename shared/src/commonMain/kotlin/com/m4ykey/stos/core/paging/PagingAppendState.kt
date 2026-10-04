@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.m4ykey.stos.core.paging

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import org.jetbrains.compose.resources.stringResource
import stos.shared.generated.resources.Res
import stos.shared.generated.resources.retry

@Composable
fun PagingAppendState(items : LazyPagingItems<*>) {
    val loadState = items.loadState

    when (val state = loadState.append) {
        is LoadState.Loading -> ContainedLoadingIndicator()
        is LoadState.Error -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = state.error.message ?: "Loading error",
                    style = MaterialTheme.typography.bodySmall
                )
                TextButton(onClick = { items.retry() }) {
                    Text(text = stringResource(Res.string.retry))
                }
            }
        }
        is LoadState.NotLoading -> Unit
    }
}