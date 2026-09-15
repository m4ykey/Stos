package com.m4ykey.stos.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {

    @Serializable
    data object QuestionHome : Route

    @Serializable
    data object Search : Route

    @Serializable
    data class QuestionDetail(val questionId : Int) : Route

}