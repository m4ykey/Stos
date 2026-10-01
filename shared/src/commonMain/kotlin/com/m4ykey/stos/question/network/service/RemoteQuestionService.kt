package com.m4ykey.stos.question.network.service

import com.m4ykey.stos.core.Filters.QUESTION_HOME_FILTER
import com.m4ykey.stos.core.Items
import com.m4ykey.stos.question.network.model.QuestionItemDto

interface RemoteQuestionService {

    suspend fun getQuestions(
        order : String = "desc",
        sort : String,
        site : String = "stackoverflow",
        filter : String = QUESTION_HOME_FILTER,
        page : Int,
        pageSize : Int
    ) : Items<QuestionItemDto>

}