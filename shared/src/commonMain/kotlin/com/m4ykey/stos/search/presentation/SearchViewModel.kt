@file:OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)

package com.m4ykey.stos.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.m4ykey.stos.question.domain.model.QuestionItem
import com.m4ykey.stos.search.domain.model.SearchSort
import com.m4ykey.stos.search.domain.repository.SearchRepository
import com.m4ykey.stos.search.presentation.state.SearchQuestionState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update

class SearchViewModel(
    private val repository : SearchRepository
) : ViewModel() {

    private val _searchState = MutableStateFlow(SearchQuestionState())
    val searchState = _searchState.asStateFlow()

    private val searchFlow : Flow<PagingData<QuestionItem>> = _searchState
        .debounce { state -> if (state.inTitle.isNullOrBlank()) 0L else 300L }
        .distinctUntilChanged()
        .flatMapLatest { state ->
            if (state.inTitle.isNullOrBlank()) {
                flowOf(PagingData.empty())
            } else {
                repository.searchQuestions(
                    inTitle = state.inTitle,
                    sort = state.sort.name
                )
            }
        }
        .cachedIn(viewModelScope)

    fun searchQuestions(query : String) : Flow<PagingData<QuestionItem>> = searchFlow

    fun updateSort(sort : SearchSort) {
        _searchState.update { it.copy(sort = sort) }
    }

    fun updateQuery(query : String) {
        _searchState.update { it.copy(inTitle = query) }
    }
}