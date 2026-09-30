@file:OptIn(ExperimentalCoroutinesApi::class)

package com.m4ykey.stos.question.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.m4ykey.stos.question.domain.model.QuestionItem
import com.m4ykey.stos.question.domain.repository.QuestionRepository
import com.m4ykey.stos.question.presentation.state.QuestionStateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class QuestionViewModel(
    private val repository : QuestionRepository
) : ViewModel() {

    private val _questionState = MutableStateFlow(QuestionStateFlow())
    val questionState = _questionState.asStateFlow()

    private val questionFlow = _questionState
        .flatMapLatest { repository.getQuestions() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PagingData.empty()
        )

    fun getQuestions() : Flow<PagingData<QuestionItem>> = questionFlow

}