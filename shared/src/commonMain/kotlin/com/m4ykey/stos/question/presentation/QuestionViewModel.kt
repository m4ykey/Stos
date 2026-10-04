@file:OptIn(ExperimentalCoroutinesApi::class)

package com.m4ykey.stos.question.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.m4ykey.stos.question.domain.model.QuestionItem
import com.m4ykey.stos.question.domain.model.QuestionSort
import com.m4ykey.stos.question.domain.repository.QuestionRepository
import com.m4ykey.stos.question.presentation.state.QuestionState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class QuestionViewModel(
    private val repository : QuestionRepository
) : ViewModel() {

    private val _questionState = MutableStateFlow(QuestionState())
    val questionState = _questionState.asStateFlow()

    private val questionFlow : Flow<PagingData<QuestionItem>> = _questionState
        .map { it.sort }
        .distinctUntilChanged()
        .flatMapLatest { sort ->
            repository.getQuestions(sort = sort.name)
        }
        .cachedIn(viewModelScope)

    fun getQuestions() : Flow<PagingData<QuestionItem>> = questionFlow

    fun updateSort(sort : QuestionSort) {
        _questionState.update { it.copy(sort = sort) }
    }
}