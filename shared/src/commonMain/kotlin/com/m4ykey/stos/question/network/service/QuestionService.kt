package com.m4ykey.stos.question.network.service

import com.m4ykey.stos.core.Items
import com.m4ykey.stos.question.network.model.QuestionDetailDto
import com.m4ykey.stos.question.network.model.QuestionItemDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments

class QuestionService(private val client : HttpClient) : RemoteQuestionService {

    override suspend fun getQuestions(
        order: String,
        sort: String,
        site: String,
        filter: String,
        page: Int,
        pageSize: Int
    ): Items<QuestionItemDto> {
        return client.get {
            url {
                appendPathSegments("questions")
                parameter("order", order)
                parameter("sort", sort)
                parameter("site", site)
                parameter("filter", filter)
                parameter("page", page)
                parameter("page_size", pageSize)
            }
        }.body()
    }

    override suspend fun getQuestionById(
        site: String,
        id: Int,
        filter : String
    ): Items<QuestionDetailDto> {
        return client.get {
            url {
                appendPathSegments("questions/$id")
                parameter("site", site)
                parameter("filter", filter)
            }
        }.body()
    }
}