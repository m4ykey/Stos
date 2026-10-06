package com.m4ykey.stos.question.network.service

import com.m4ykey.stos.core.Filters.QUESTION_DETAIL_FILTER
import com.m4ykey.stos.core.Filters.QUESTION_HOME_FILTER
import com.m4ykey.stos.core.Items
import com.m4ykey.stos.question.network.model.QuestionDetailDto
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

    suspend fun getQuestionById(
        site : String = "stackoverflow",
        id : Int,
        filter : String = QUESTION_DETAIL_FILTER
    ) : Items<QuestionDetailDto>

}