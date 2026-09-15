package com.m4ykey.stos.question.data.network.service

import com.m4ykey.stos.question.data.network.dto.QuestionDto

interface RemoteQuestionService {

    suspend fun getQuestions(
        order : String = "desc",
        sort : String,
        site : String,
        filter : String = "!1GI*BjFh14KlJAVLV3i*gX4-)2NZI8T5fwhvRXfUn0qmg8)SN",
        page: Int,
        pageSize : Int
    ) : QuestionDto

}