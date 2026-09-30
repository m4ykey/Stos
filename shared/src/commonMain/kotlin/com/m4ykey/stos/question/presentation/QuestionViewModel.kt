package com.m4ykey.stos.question.presentation

import androidx.lifecycle.ViewModel
import com.m4ykey.stos.question.domain.repository.QuestionRepository
import com.m4ykey.stos.question.presentation.state.QuestionStateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuestionViewModel(
    private val repository : QuestionRepository
) : ViewModel() {

    //private val _questionState = MutableStateFlow(QuestionStateFlow())
    //val questionState = _questionState.asStateFlow()

}