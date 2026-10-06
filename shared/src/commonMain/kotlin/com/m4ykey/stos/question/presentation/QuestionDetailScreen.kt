package com.m4ykey.stos.question.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.m4ykey.stos.core.ui.ActionButton
import com.m4ykey.stos.core.ui.AppScaffold
import com.m4ykey.text_markdown.TextMarkdown
import org.koin.compose.viewmodel.koinViewModel
import stos.shared.generated.resources.Res
import stos.shared.generated.resources.back
import stos.shared.generated.resources.ic_arrow_back

@Composable
fun QuestionDetailScreen(
    modifier : Modifier = Modifier,
    onBack : () -> Unit,
    questionId : Int,
    viewModel: QuestionViewModel = koinViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val listState = rememberLazyListState()

    AppScaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        scrollBehavior = scrollBehavior,
        content = {
            QuestionDetailContent(
                viewModel = viewModel,
                listState = listState,
                questionId = questionId
            )
        },
        navigation = {
            ActionButton(
                icon = Res.drawable.ic_arrow_back,
                text = Res.string.back,
                onClick = onBack
            )
        }
    )
}

@Composable
fun QuestionDetailContent(
    viewModel: QuestionViewModel,
    listState : LazyListState,
    questionId: Int
) {
    val item by viewModel.questionDetailState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getQuestionDetail(questionId)
    }

    LazyColumn(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        state = listState
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                TextMarkdown(
                    alignment = Alignment.TopStart,
                    text = item.item?.title.orEmpty(),
                    fontSize = 20.sp
                )
                TextMarkdown(
                    alignment = Alignment.TopStart,
                    text = item.item?.bodyMarkdown.orEmpty()
                )
            }
        }
    }
}