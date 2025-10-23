package com.m4ykey.stos.question.presentation.list

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.m4ykey.stos.question.domain.model.Question
import com.m4ykey.stos.question.domain.usecase.QuestionUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class QuestionListViewModel(
    private val useCase: QuestionUseCase
) : ViewModel() {

    private var page = 1
    private var pageSize = 20

    private val _questionListState = MutableStateFlow(QuestionListState())
    val questionListState = _questionListState.asStateFlow()

    fun getQuestionsFlow() : Flow<PagingData<Question>> = _questionFlow

    fun updateSort(sort : QuestionSort) {
        _questionListState.update { it.copy(sort = sort) }
    }

    private val _questionFlow = _questionListState
        .map { it.sort to it.order }
        .distinctUntilChanged()
        .debounce(1000L)
        .flatMapLatest { (sort, order) ->
            useCase.getQuestions(
                page = page,
                pageSize = pageSize,
                sort = sort.name
            )
        }

}