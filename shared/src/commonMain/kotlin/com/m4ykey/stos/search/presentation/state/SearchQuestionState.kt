package com.m4ykey.stos.search.presentation.state

import com.m4ykey.stos.search.domain.model.SearchSort

data class SearchQuestionState(
    val inTitle : String? = null,
    val sort : SearchSort = SearchSort.ACTIVITY
)
