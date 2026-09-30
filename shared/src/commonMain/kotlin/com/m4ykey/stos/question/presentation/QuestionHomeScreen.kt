package com.m4ykey.stos.question.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.m4ykey.stos.question.presentation.components.QuestionItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun QuestionHomeScreen(
    modifier : Modifier = Modifier,
    onSearchClick : () -> Unit,
    viewModel: QuestionViewModel = koinViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("test") })
        }
    ) { innerPadding ->
        QuestionContent(
            modifier = Modifier.padding(innerPadding),
            viewModel = viewModel
        )
    }
//    AppScaffold(
//        content = { QuestionContent(
//            viewModel = viewModel
//        ) }
//    )
}

@Composable
fun QuestionContent(
    modifier: Modifier = Modifier,
    viewModel: QuestionViewModel
) {
    val questions = viewModel.getQuestions().collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            count = questions.itemCount
        ) { index ->
            val question = questions[index]

            question?.let { item ->
                QuestionItem(
                    onOwnerClick = {},
                    onQuestionClick = {},
                    item = item
                )
            }
        }
    }
}