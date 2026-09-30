package com.m4ykey.stos.question.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.m4ykey.stos.core.ui.AppScaffold

@Composable
fun QuestionHomeScreen(
    modifier : Modifier = Modifier,
    onSearchClick : () -> Unit,
    viewModel: QuestionViewModel = viewModel()
) {
    AppScaffold(
        content = { QuestionContent(
            viewModel = viewModel
        ) }
    )
}

@Composable
fun QuestionContent(
    modifier: Modifier = Modifier,
    viewModel: QuestionViewModel
) {


    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

    }
}