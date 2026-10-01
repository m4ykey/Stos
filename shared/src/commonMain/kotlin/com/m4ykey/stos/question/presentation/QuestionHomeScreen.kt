package com.m4ykey.stos.question.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.m4ykey.stos.core.ui.AppScaffold
import com.m4ykey.stos.question.domain.model.QuestionSort
import com.m4ykey.stos.question.presentation.components.ChipList
import com.m4ykey.stos.question.presentation.components.QuestionItem
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import stos.shared.generated.resources.Res
import stos.shared.generated.resources.ic_search

@Composable
fun QuestionHomeScreen(
    modifier : Modifier = Modifier,
    onSearchClick : () -> Unit,
    viewModel: QuestionViewModel = koinViewModel(),
    onQuestionClick: (Int) -> Unit,
    onOwnerClick: (Int) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val listState = rememberLazyListState()

    AppScaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        scrollBehavior = scrollBehavior,
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    contentDescription = null,
                    painter = painterResource(Res.drawable.ic_search),
                    modifier = modifier.size(24.dp)
                )
            }
        },
        content = {
            QuestionContent(
                viewModel = viewModel,
                onQuestionClick = onQuestionClick,
                onOwnerClick = onOwnerClick,
                listState = listState
            )
        }
    )
}

@Composable
fun QuestionContent(
    modifier: Modifier = Modifier,
    viewModel: QuestionViewModel,
    onOwnerClick : (Int) -> Unit,
    onQuestionClick : (Int) -> Unit,
    listState : LazyListState,
    availableSorts : List<QuestionSort> = QuestionSort.entries
) {
    val questions = viewModel.getQuestions().collectAsLazyPagingItems()
    val viewState by viewModel.questionState.collectAsStateWithLifecycle()
    val sort = viewState.sort

    var shouldScrollAfterRefresh by remember { mutableStateOf(false) }

    LaunchedEffect(questions.loadState.refresh) {
        if (shouldScrollAfterRefresh && questions.loadState.refresh is LoadState.NotLoading) {
            shouldScrollAfterRefresh = false
            listState.animateScrollToItem(0)
        }
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            ChipList(
                modifier = Modifier.fillMaxWidth(),
                availableSorts = availableSorts,
                selectedChip = sort,
                onChipSelected = { selectedSort ->
                    shouldScrollAfterRefresh = true
                    viewModel.updateSort(selectedSort)
                }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 10.dp),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(
                count = questions.itemCount,
                key = questions.itemKey { it.questionId }
            ) { index ->
                val question = questions[index]

                question?.let { item ->
                    QuestionItem(
                        onOwnerClick = onOwnerClick,
                        onQuestionClick = onQuestionClick,
                        item = item
                    )
                }
            }
        }
    }
}