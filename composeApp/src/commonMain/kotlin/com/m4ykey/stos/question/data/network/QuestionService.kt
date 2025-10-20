package com.m4ykey.stos.question.data.network

import com.m4ykey.stos.core.model.Items
import com.m4ykey.stos.core.network.setParameters
import com.m4ykey.stos.question.data.network.model.QuestionDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments

class QuestionService(
    private val client : HttpClient
) : RemoteQuestionService {

    override suspend fun getQuestions(
        page: Int,
        pageSize: Int,
        filter: String,
        sort: String,
        order: String
    ): Items<QuestionDto> {
        return client.get {
            url {
                appendPathSegments("questions")
                setParameters(
                    "page" to page,
                    "pagesize" to pageSize,
                    "filter" to filter,
                    "sort" to sort,
                    "order" to order
                )
            }
        }.body()
    }
}