package com.m4ykey.stos.question.presentation.state

import androidx.paging.compose.LazyPagingItems
import com.m4ykey.stos.question.domain.model.QuestionItem

data class QuestionStateFlow(
    val isLoading : Boolean = false,
    val error : String? = null,
    val items : LazyPagingItems<QuestionItem>
)