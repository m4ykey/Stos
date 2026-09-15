@file:OptIn(ExperimentalCoroutinesApi::class)

package com.m4ykey.stos.question.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.m4ykey.stos.question.domain.repository.QuestionRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class QuestionViewModel(
    private val repository: QuestionRepository
) : ViewModel() {

    private val _questionListState = MutableStateFlow(QuestionListState())
    val questionListState = _questionListState.asStateFlow()

    val questions = _questionListState
        .map { it.sort }
        .distinctUntilChanged()
        .flatMapLatest { sort ->
            repository.getQuestions(sort = sort.name)
        }
        .cachedIn(viewModelScope)

    fun updateSort(sort: QuestionSort) {
        _questionListState.update { it.copy(sort = sort) }
    }

}