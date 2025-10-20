package com.m4ykey.stos.app.navigation

sealed class Route(val route : String) {

    object QuestionHome : Route(route = "question_home")

}