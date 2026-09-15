package com.m4ykey.stos.question.service

import com.m4ykey.stos.question.dto.QuestionDto

interface RemoteQuestionService {

    suspend fun getQuestions(
        order : String,
        sort : String,
        site : String,
        filter : String = "!)GS)TNzKf-QUHHuVAWvIHfl6Bb(v_6Kx-hDNc5kwcRT7CiGD1VMI",
        page: Int,
        pageSize : Int
    ) : QuestionDto

}