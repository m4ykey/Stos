package com.m4ykey.stos.search.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.m4ykey.stos.core.paging.PagingAppendState
import com.m4ykey.stos.core.ui.ActionButton
import com.m4ykey.stos.core.ui.AppScaffold
import com.m4ykey.stos.core.ui.ErrorItem
import com.m4ykey.stos.core.ui.LoadingItem
import com.m4ykey.stos.question.presentation.components.ChipList
import com.m4ykey.stos.question.presentation.components.QuestionItem
import com.m4ykey.stos.search.domain.model.SearchSort
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import stos.shared.generated.resources.Res
import stos.shared.generated.resources.back
import stos.shared.generated.resources.ic_arrow_back
import stos.shared.generated.resources.ic_arrow_up

@Composable
fun SearchListScreen(
    modifier : Modifier = Modifier,
    onBack : () -> Unit,
    inTitle : String,
    viewModel: SearchViewModel = koinViewModel(),
    onQuestionClick : (Int) -> Unit,
    onOwnerClick : (Int) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val showScrollByPosition by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 20
        }
    }

    AppScaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        scrollBehavior = scrollBehavior,
        floatingActionButton = {
            AnimatedVisibility(
                visible = showScrollByPosition,
                enter = slideInHorizontally { it },
                exit = slideOutHorizontally { it }
            ) {
                FloatingActionButton(
                    onClick = {
                        coroutineScope.launch {
                            listState.animateScrollToItem(0)
                        }
                    },
                    content = {
                        Icon(
                            contentDescription = null,
                            painter = painterResource(Res.drawable.ic_arrow_up),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )
            }
        },
        navigation = {
            ActionButton(
                onClick = onBack,
                text = Res.string.back,
                icon = Res.drawable.ic_arrow_back
            )
        },
        content = {
            SearchListScreenContent(
                viewModel = viewModel,
                listState = listState,
                inTitle = inTitle,
                onOwnerClick = onOwnerClick,
                onQuestionClick = onQuestionClick
            )
        }
    )
}

@Composable
fun SearchListScreenContent(
    modifier : Modifier = Modifier,
    viewModel: SearchViewModel,
    listState: LazyListState,
    inTitle: String,
    onOwnerClick: (Int) -> Unit,
    onQuestionClick: (Int) -> Unit
) {
    val items = viewModel.searchQuestions(inTitle).collectAsLazyPagingItems()
    val viewState by viewModel.searchState.collectAsStateWithLifecycle()
    val sort = viewState.sort
    val currentQuery = viewState.inTitle ?: ""

    var shouldScrollAfterRefresh by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.updateQuery(inTitle)
    }

    LaunchedEffect(items.loadState.refresh) {
        if (shouldScrollAfterRefresh && items.loadState.refresh is LoadState.NotLoading) {
            shouldScrollAfterRefresh = false
            listState.animateScrollToItem(0)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchText(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            value = currentQuery,
            onValueChange = { viewModel.updateQuery(it) },
            onSearch = { viewModel.updateQuery(currentQuery) }
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            ChipList(
                modifier = Modifier.fillMaxWidth(),
                availableSorts = SearchSort.entries,
                selectedChip = sort,
                onChipSelected = { selectedSort ->
                    shouldScrollAfterRefresh = true
                    viewModel.updateSort(selectedSort)
                }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        when (val loadState = items.loadState.refresh) {
            is LoadState.Error -> {
                ErrorItem(
                    message = loadState.error.message ?: "Loading error",
                    onRetry = { items.retry() }
                )
            }
            is LoadState.Loading -> LoadingItem()
            is LoadState.NotLoading -> {
                LazyColumn(
                    state = listState,
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp)
                ) {
                    items(
                        count = items.itemCount,
                        key = items.itemKey { it.questionId }
                    ) { index ->
                        val question = items[index]

                        question?.let { item ->
                            QuestionItem(
                                item = item,
                                onOwnerClick = onOwnerClick,
                                onQuestionClick = onQuestionClick
                            )
                        }
                    }

                    item {
                        PagingAppendState(items = items)
                    }
                }
            }
        }
    }
}