@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.m4ykey.stos.core.paging

import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.m4ykey.stos.core.ui.ErrorItem

@Composable
fun PagingAppendState(items : LazyPagingItems<*>) {
    val loadState = items.loadState

    when (val state = loadState.append) {
        is LoadState.Loading -> ContainedLoadingIndicator()
        is LoadState.Error -> {
            ErrorItem(
                message = state.error.message ?: "Loading error",
                onRetry = { items.retry() }
            )
        }
        is LoadState.NotLoading -> Unit
    }
}