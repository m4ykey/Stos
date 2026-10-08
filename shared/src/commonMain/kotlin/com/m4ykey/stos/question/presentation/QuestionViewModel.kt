@file:OptIn(ExperimentalCoroutinesApi::class)

package com.m4ykey.stos.question.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.m4ykey.stos.core.network.ApiResult
import com.m4ykey.stos.question.domain.model.QuestionItem
import com.m4ykey.stos.question.domain.model.QuestionSort
import com.m4ykey.stos.question.domain.repository.QuestionRepository
import com.m4ykey.stos.question.presentation.state.QuestionDetailState
import com.m4ykey.stos.question.presentation.state.QuestionState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuestionViewModel(
    private val repository : QuestionRepository
) : ViewModel() {

    private val _questionState = MutableStateFlow(QuestionState())
    val questionState = _questionState.asStateFlow()

    private val _questionDetailState = MutableStateFlow(QuestionDetailState())
    val questionDetailState = _questionDetailState.asStateFlow()

    fun getQuestionDetail(questionId : Int) {
        viewModelScope.launch {
            repository.getQuestionById(questionId)
                .onStart {
                    _questionDetailState.update {
                        it.copy(
                            isLoading = true,
                            error = null
                        )
                    }
                }
                .catch { e ->
                    _questionDetailState.update {
                        it.copy(
                            isLoading = false,
                            error = e.message
                        )
                    }
                }
                .collect { result ->
                    when (result) {
                        is ApiResult.Success -> {
                            _questionDetailState.update {
                                it.copy(
                                    isLoading = false,
                                    error = null,
                                    item = result.data
                                )
                            }
                        }
                        is ApiResult.Failure -> {
                            _questionDetailState.update {
                                it.copy(
                                    isLoading = false,
                                    error = result.exception.message
                                )
                            }
                        }
                    }
                }
        }
    }

    fun onRetryDetailState(questionId : Int) {
        getQuestionDetail(questionId)
    }

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