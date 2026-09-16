package com.m4ykey.stos.question.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.m4ykey.stos.question.presentation.component.QuestionItem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import stos.shared.generated.resources.Res
import stos.shared.generated.resources.ic_search
import stos.shared.generated.resources.search

@Composable
fun QuestionScreen(
    modifier : Modifier = Modifier,
    onSearchClick : () -> Unit,
    onQuestionClick : (Int) -> Unit,
    viewModel: QuestionViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                actions = {
                    IconButton(onClick = onSearchClick) {
                        Icon(
                            contentDescription = stringResource(Res.string.search),
                            painter = painterResource(Res.drawable.ic_search)
                        )
                    }
                }
            )
        }
    ) {
        QuestionContent(
            modifier = Modifier.fillMaxSize(),
            viewModel = viewModel,
            onQuestionClick = {}
        )
    }
}

@Composable
fun QuestionContent(
    modifier : Modifier = Modifier,
    onQuestionClick: (Int) -> Unit,
    viewModel: QuestionViewModel
) {

    val questions = viewModel.questions.collectAsLazyPagingItems()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn() {
            items(count = questions.itemCount) { index ->
                questions[index]?.let { question ->
                    QuestionItem(item = question)
                }
            }
        }
    }
}