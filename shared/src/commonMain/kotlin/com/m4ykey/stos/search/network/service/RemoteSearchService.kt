package com.m4ykey.stos.search.network.service

import com.m4ykey.stos.core.Filters.QUESTION_HOME_FILTER
import com.m4ykey.stos.core.Items
import com.m4ykey.stos.question.network.model.QuestionItemDto

interface RemoteSearchService {

    suspend fun searchQuestions(
        order : String = "desc",
        sort : String,
        site : String = "stackoverflow",
        filter : String = QUESTION_HOME_FILTER,
        page : Int,
        pageSize : Int,
        inTitle : String
    ) : Items<QuestionItemDto>

}